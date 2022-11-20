package ru.melonhell.mcdebugrender.core.shape;

import org.jetbrains.annotations.NotNull;
import ru.melonhell.mcdebugrender.core.utils.binary.BinaryWriter;

public interface Shape {
    static Box.Builder box() {
        return new Box.Builder();
    }

    static Line.Builder line() {
        return new Line.Builder();
    }

    static Text.Builder text() {
        return new Text.Builder();
    }

    void write(@NotNull BinaryWriter buffer);
}
