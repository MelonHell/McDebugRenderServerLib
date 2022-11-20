package ru.melonhell.mcdebugrender.core.shape;

import org.jetbrains.annotations.NotNull;
import ru.melonhell.mcdebugrender.core.Layer;
import ru.melonhell.mcdebugrender.core.utils.MdrColor;
import ru.melonhell.mcdebugrender.core.utils.MdrPoint;
import ru.melonhell.mcdebugrender.core.utils.binary.BinaryWriter;

import java.util.Objects;

public record Box(
        MdrPoint start,
        MdrPoint end,
        MdrColor color,
        Layer layer
) implements Shape {
    private static final int ID = 0;

    @Override
    public void write(@NotNull BinaryWriter buffer) {
        buffer.writeVarInt(ID);
        buffer.writeDouble(start.x());
        buffer.writeDouble(start.y());
        buffer.writeDouble(start.z());
        buffer.writeDouble(end.x());
        buffer.writeDouble(end.y());
        buffer.writeDouble(end.z());
        buffer.writeInt(color.asARGB());
        buffer.writeVarInt(layer.ordinal());
    }

    public static class Builder {
        private MdrPoint start;
        private MdrPoint end;
        private MdrColor color = MdrColor.WHITE;
        private Layer layer = Layer.INLINE;

        public Builder start(MdrPoint start) {
            this.start = start;
            return this;
        }

        public Builder end(MdrPoint end) {
            this.end = end;
            return this;
        }

        public Builder color(MdrColor color) {
            this.color = color;
            return this;
        }

        public Builder layer(Layer layer) {
            this.layer = layer;
            return this;
        }

        public Box build() {
            Objects.requireNonNull(start);
            Objects.requireNonNull(end);
            return new Box(start, end, color, layer);
        }
    }
}
