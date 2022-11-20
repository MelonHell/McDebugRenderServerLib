package ru.melonhell.mcdebugrender.minestom;

import ru.melonhell.mcdebugrender.core.DebugMessage;
import ru.melonhell.mcdebugrender.core.DebugMessageBuilder;

public class MinestomDebugMessageBuilder extends DebugMessageBuilder {
    @Override
    public DebugMessage build() {
        return new MinestomDebugMessage(ops);
    }
}
