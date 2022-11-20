package ru.melonhell.mcdebugrender.core.shape;

import org.jetbrains.annotations.NotNull;
import ru.melonhell.mcdebugrender.core.Layer;
import ru.melonhell.mcdebugrender.core.utils.MdrColor;
import ru.melonhell.mcdebugrender.core.utils.MdrPoint;
import ru.melonhell.mcdebugrender.core.utils.binary.BinaryWriter;

import java.util.Objects;

public record Text(
        MdrPoint position,
        String content,
        MdrColor color,
        float size,
        Layer layer
) implements Shape {
    private static final int ID = 2;

    @Override
    public void write(@NotNull BinaryWriter buffer) {
        buffer.writeVarInt(ID);
        buffer.writeDouble(position.x());
        buffer.writeDouble(position.y());
        buffer.writeDouble(position.z());
        buffer.writeSizedString(content);
        buffer.writeInt(color.asARGB());
        buffer.writeFloat(size);
        buffer.writeVarInt(layer.ordinal());
    }

    public static class Builder {
        private MdrPoint position;
        private String content;
        private MdrColor color = MdrColor.WHITE;
        private float size = 0.02f;
        private Layer layer = Layer.INLINE;

        public Builder position(MdrPoint position) {
            this.position = position;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder color(MdrColor color) {
            this.color = color;
            return this;
        }

        public Builder size(float size) {
            this.size = size;
            return this;
        }

        public Builder layer(Layer layer) {
            this.layer = layer;
            return this;
        }

        public Text build() {
            Objects.requireNonNull(position);
            Objects.requireNonNull(content);
            return new Text(position, content, color, size, layer);
        }
    }
}
