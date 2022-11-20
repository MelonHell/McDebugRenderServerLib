package ru.melonhell.mcdebugrender.bukkit;

import ru.melonhell.mcdebugrender.core.DebugMessage;
import ru.melonhell.mcdebugrender.core.DebugMessageBuilder;

public class BukkitDebugMessageBuilder extends DebugMessageBuilder {
    @Override
    public DebugMessage build() {
        return new BukkitDebugMessage(ops);
    }
}
