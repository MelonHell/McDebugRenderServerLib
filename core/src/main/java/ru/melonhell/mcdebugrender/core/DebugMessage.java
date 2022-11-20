package ru.melonhell.mcdebugrender.core;

import ru.melonhell.mcdebugrender.core.utils.MdrNamespaceID;
import ru.melonhell.mcdebugrender.core.utils.binary.BinaryWriter;

import java.util.List;

public abstract class DebugMessage {
    protected final List<Operation> ops;

    public DebugMessage(List<Operation> ops) {
        this.ops = ops;
    }

    public PluginMessagePacketContainer getPacket() {
        BinaryWriter writer = new BinaryWriter(1024);
        writer.writeVarInt(ops.size());
        for (Operation op : ops) {
            op.write(writer);
        }
        return new PluginMessagePacketContainer(new MdrNamespaceID("debug", "shapes"), writer.toByteArray());
    }

    public abstract void sendTo(Object receiver);
}
