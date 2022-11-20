package ru.melonhell.mcdebugrender.core;

import ru.melonhell.mcdebugrender.core.utils.MdrNamespaceID;

public record PluginMessagePacketContainer(
        MdrNamespaceID channel,
        byte[] data
) {
}
