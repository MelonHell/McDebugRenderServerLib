package ru.melonhell.mcdebugrender.core;

import org.jetbrains.annotations.NotNull;
import ru.melonhell.mcdebugrender.core.shape.Shape;
import ru.melonhell.mcdebugrender.core.utils.MdrNamespaceID;
import ru.melonhell.mcdebugrender.core.utils.binary.BinaryWriter;

public interface Operation {

    void write(@NotNull BinaryWriter buffer);

    record Set(MdrNamespaceID id, Shape shape) implements Operation {
        private static final int ID = 0;

        @Override
        public void write(@NotNull BinaryWriter buffer) {
            buffer.writeVarInt(ID);
            buffer.writeSizedString(id.asString());
            shape.write(buffer);
        }
    }

    record Remove(@NotNull MdrNamespaceID id) implements Operation {
        private static final int ID = 1;

        @Override
        public void write(@NotNull BinaryWriter buffer) {
            buffer.writeVarInt(ID);
            buffer.writeSizedString(id.asString());
        }
    }

    record ClearNS(@NotNull String namespace) implements Operation {
        private static final int ID = 2;

        @Override
        public void write(@NotNull BinaryWriter buffer) {
            buffer.writeVarInt(ID);
            buffer.writeSizedString(namespace);
        }
    }

    final class Clear implements Operation {
        private static final int ID = 3;

        @Override
        public void write(@NotNull BinaryWriter buffer) {
            buffer.writeVarInt(ID);
        }
    }


}
