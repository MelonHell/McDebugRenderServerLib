package ru.melonhell.mcdebugrender.bukkit;

import com.comphenix.protocol.utility.ZeroBuffer;

public class ArrayByteBuf extends ZeroBuffer {
    private final byte[] array;

    public ArrayByteBuf(byte[] array) {
        this.array = array;
    }

    @Override
    public boolean hasArray() {
        return true;
    }

    @Override
    public byte[] array() {
        return array;
    }
}
