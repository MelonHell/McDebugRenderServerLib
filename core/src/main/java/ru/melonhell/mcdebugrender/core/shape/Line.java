package ru.melonhell.mcdebugrender.core.shape;

import org.jetbrains.annotations.NotNull;
import ru.melonhell.mcdebugrender.core.Layer;
import ru.melonhell.mcdebugrender.core.utils.MdrColor;
import ru.melonhell.mcdebugrender.core.utils.MdrPoint;
import ru.melonhell.mcdebugrender.core.utils.binary.BinaryWriter;

import java.util.ArrayList;
import java.util.List;

public record Line(
        List<MdrPoint> points,
        float thickness,
        MdrColor color,
        Layer layer
) implements Shape {
    private static final int ID = 1;

    @Override
    public void write(@NotNull BinaryWriter buffer) {
        buffer.writeVarInt(ID);
        buffer.writeVarInt(points.size());
        for (MdrPoint point : points) {
            buffer.writeDouble(point.x());
            buffer.writeDouble(point.y());
            buffer.writeDouble(point.z());
        }
        buffer.writeFloat(thickness);
        buffer.writeInt(color.asARGB());
        buffer.writeVarInt(layer.ordinal());
    }

    public static class Builder {
        private final List<MdrPoint> points = new ArrayList<>();
        private float thickness = 0.1f;
        private MdrColor color = MdrColor.WHITE;
        private Layer layer = Layer.INLINE;

        public Builder point(MdrPoint point) {
            points.add(point);
            return this;
        }

        public Builder thickness(float thickness) {
            this.thickness = thickness;
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

        public Line build() {
            if (points.size() < 2) throw new RuntimeException("Line must have at least 2 points");
            return new Line(points, thickness, color, layer);
        }
    }
}
