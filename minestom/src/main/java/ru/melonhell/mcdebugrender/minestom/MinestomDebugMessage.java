package ru.melonhell.mcdebugrender.minestom;

import net.kyori.adventure.audience.Audience;
import net.minestom.server.network.packet.server.play.PluginMessagePacket;
import net.minestom.server.utils.PacketUtils;
import ru.melonhell.mcdebugrender.core.DebugMessage;
import ru.melonhell.mcdebugrender.core.Operation;
import ru.melonhell.mcdebugrender.core.PluginMessagePacketContainer;

import java.util.List;

public class MinestomDebugMessage extends DebugMessage {
    public MinestomDebugMessage(List<Operation> ops) {
        super(ops);
    }

    @Override
    public void sendTo(Object receiver) {
        if (receiver instanceof Audience audience) {
            PacketUtils.sendPacket(audience, getMinestomPacket());
            return;
        }
        throw new RuntimeException("receiver must be net.kyori.adventure.audience.Audience");
    }

    public PluginMessagePacket getMinestomPacket() {
        PluginMessagePacketContainer packet = getPacket();
        return new PluginMessagePacket(packet.channel().asString(), packet.data());
    }
}
