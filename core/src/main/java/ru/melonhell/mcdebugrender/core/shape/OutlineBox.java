package ru.melonhell.mcdebugrender.core.shape;

import org.jetbrains.annotations.NotNull;
import ru.melonhell.mcdebugrender.core.Layer;
import ru.melonhell.mcdebugrender.core.utils.MdrColor;
import ru.melonhell.mcdebugrender.core.utils.MdrPoint;
import ru.melonhell.mcdebugrender.core.utils.binary.BinaryWriter;

import java.util.Objects;

public record OutlineBox(
        MdrPoint start,
        MdrPoint end,
        MdrColor color,
        Layer layer,
        MdrColor colorLine,
        Layer layerLine,
        String text,
        MdrColor colorText
) implements Shape {
    private static final int ID = 3;

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
        buffer.writeInt(colorLine.asARGB());
        buffer.writeVarInt(layerLine.ordinal());
        buffer.writeBoolean(text != null);
        if (text != null) {
            buffer.writeSizedString(text);
            buffer.writeInt(colorText.asARGB());
        }
    }

    public static class Builder {
        private MdrPoint start;
        private MdrPoint end;
        private MdrColor color = MdrColor.WHITE;
        private Layer layer = Layer.INLINE;
        private MdrColor colorLine = MdrColor.WHITE;
        private Layer layerLine = Layer.INLINE;
        private String text = null;
        private MdrColor colorText = MdrColor.WHITE;

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

        public Builder colorLine(MdrColor color) {
            this.colorLine = color;
            return this;
        }

        public Builder layerLine(Layer layer) {
            this.layerLine = layer;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder colorText(MdrColor color) {
            this.colorText = color;
            return this;
        }

        public OutlineBox build() {
            Objects.requireNonNull(start);
            Objects.requireNonNull(end);
            return new OutlineBox(start, end, color, layer, colorLine, layerLine, text, colorText);
        }
    }
}
