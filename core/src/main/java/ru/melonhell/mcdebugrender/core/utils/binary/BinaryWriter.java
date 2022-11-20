package ru.melonhell.mcdebugrender.core.utils.binary;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Class used to write to a byte array.
 * WARNING: not thread-safe.
 */
public class BinaryWriter extends OutputStream {
    private final NetworkBuffer buffer;

    public BinaryWriter(@NotNull NetworkBuffer buffer) {
        this.buffer = buffer;
    }

    private BinaryWriter(ByteBuffer buffer, boolean resizable) {
        this.buffer = new NetworkBuffer(buffer, resizable);
    }

    public BinaryWriter(@NotNull ByteBuffer buffer) {
        this.buffer = new NetworkBuffer(buffer);
    }

    public BinaryWriter(int initialCapacity) {
        this(ByteBuffer.allocate(initialCapacity));
    }

    public BinaryWriter() {
        this(255);
    }

    @ApiStatus.Experimental
    public static BinaryWriter view(ByteBuffer buffer) {
        return new BinaryWriter(buffer, false);
    }

    public void writeByte(byte b) {
        this.buffer.write(NetworkBuffer.BYTE, b);
    }

    public void writeBoolean(boolean b) {
        this.buffer.write(NetworkBuffer.BOOLEAN, b);
    }

    public void writeShort(short s) {
        this.buffer.write(NetworkBuffer.SHORT, s);
    }

    public void writeInt(int i) {
        this.buffer.write(NetworkBuffer.INT, i);
    }

    public void writeLong(long l) {
        this.buffer.write(NetworkBuffer.LONG, l);
    }

    public void writeFloat(float f) {
        this.buffer.write(NetworkBuffer.FLOAT, f);
    }

    public void writeDouble(double d) {
        this.buffer.write(NetworkBuffer.DOUBLE, d);
    }

    public void writeVarInt(int i) {
        this.buffer.write(NetworkBuffer.VAR_INT, i);
    }

    public void writeVarLong(long l) {
        this.buffer.write(NetworkBuffer.VAR_LONG, l);
    }

    public void writeSizedString(@NotNull String string) {
        this.buffer.write(NetworkBuffer.STRING, string);
    }

    public void writeNullTerminatedString(@NotNull String string, @NotNull Charset charset) {
        final var bytes = (string + '\0').getBytes(charset);
        writeBytes(bytes);
    }

    public void writeVarIntArray(int[] array) {
        if (array == null) {
            writeVarInt(0);
            return;
        }
        writeVarInt(array.length);
        for (int element : array) {
            writeVarInt(element);
        }
    }

    public void writeVarLongArray(long[] array) {
        if (array == null) {
            writeVarInt(0);
            return;
        }
        writeVarInt(array.length);
        for (long element : array) {
            writeVarLong(element);
        }
    }

    public void writeLongArray(long[] array) {
        if (array == null) {
            writeVarInt(0);
            return;
        }
        writeVarInt(array.length);
        for (long element : array) {
            writeLong(element);
        }
    }

    public void writeByteArray(byte[] array) {
        if (array == null) {
            writeVarInt(0);
            return;
        }
        writeVarInt(array.length);
        writeBytes(array);
    }

    public void writeBytes(byte @NotNull [] bytes) {
        this.buffer.write(NetworkBuffer.RAW_BYTES, bytes);
    }

    public void writeStringArray(@NotNull String[] array) {
        this.buffer.writeCollection(NetworkBuffer.STRING, array);
    }

    public void write(@NotNull ByteBuffer buffer) {
        byte[] remaining = new byte[buffer.remaining()];
        buffer.get(remaining);
        writeBytes(remaining);
    }

    public void write(@NotNull BinaryWriter writer) {
        writeBytes(writer.toByteArray());
    }


    public <T> void writeVarIntList(Collection<T> list, @NotNull BiConsumer<BinaryWriter, T> consumer) {
        writeVarInt(list.size());
        writeList(list, consumer);
    }

    public <T> void writeByteList(Collection<T> list, @NotNull BiConsumer<BinaryWriter, T> consumer) {
        writeByte((byte) list.size());
        writeList(list, consumer);
    }

    private <T> void writeList(Collection<T> list, @NotNull BiConsumer<BinaryWriter, T> consumer) {
        for (T t : list) consumer.accept(this, t);
    }

    /**
     * Converts the internal buffer to a byte array.
     *
     * @return the byte array containing all the {@link BinaryWriter} data
     */
    public byte[] toByteArray() {
        byte[] bytes = new byte[buffer.writeIndex()];
        this.buffer.copyTo(0, bytes, 0, bytes.length);
        return bytes;
    }

    @Override
    public void write(int b) {
        writeByte((byte) b);
    }

    public void writeUnsignedShort(int yourShort) {
        this.buffer.write(NetworkBuffer.SHORT, (short) (yourShort & 0xFFFF));
    }

    /**
     * Returns a byte[] with the contents written via BinaryWriter
     */
    public static byte[] makeArray(@NotNull Consumer<@NotNull BinaryWriter> writing) {
        BinaryWriter writer = new BinaryWriter();
        writing.accept(writer);
        return writer.toByteArray();
    }
}
