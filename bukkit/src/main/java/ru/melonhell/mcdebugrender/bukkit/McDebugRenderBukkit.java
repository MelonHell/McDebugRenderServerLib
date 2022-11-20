package ru.melonhell.mcdebugrender.bukkit;

import org.bukkit.util.Vector;
import ru.melonhell.mcdebugrender.core.utils.MdrPoint;

public class McDebugRenderBukkit {
    public static BukkitDebugMessageBuilder builder() {
        return new BukkitDebugMessageBuilder();
    }

    public static MdrPoint toPoint(Vector vector) {
        return new MdrPoint(vector.getX(), vector.getY(), vector.getZ());
    }
}
