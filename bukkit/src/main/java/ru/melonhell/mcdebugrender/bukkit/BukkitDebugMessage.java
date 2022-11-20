package ru.melonhell.mcdebugrender.bukkit;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.MinecraftKey;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import ru.melonhell.mcdebugrender.core.DebugMessage;
import ru.melonhell.mcdebugrender.core.Operation;
import ru.melonhell.mcdebugrender.core.PluginMessagePacketContainer;

import java.lang.reflect.Constructor;
import java.util.List;

public class BukkitDebugMessage extends DebugMessage {

    private static final Constructor<?> friendlyByteByfConstructor;

    static {
        try {
            friendlyByteByfConstructor = MinecraftReflection.getPacketDataSerializerClass().getConstructor(ByteBuf.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public BukkitDebugMessage(List<Operation> ops) {
        super(ops);
    }

    @Override
    public void sendTo(Object receiver) {
        if (receiver instanceof Player player) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, getProtocolLibPacket());
            return;
        }
        throw new RuntimeException("receiver must be org.bukkit.entity.Player");
    }

    @SneakyThrows
    public PacketContainer getProtocolLibPacket() {
        PluginMessagePacketContainer packet = getPacket();
        PacketContainer container = new PacketContainer(PacketType.Play.Server.CUSTOM_PAYLOAD);
        ByteBuf byteBuf = Unpooled.copiedBuffer(packet.data());
        Object friendlyByteBuf = friendlyByteByfConstructor.newInstance(byteBuf);
        container.getMinecraftKeys().write(0, new MinecraftKey(packet.channel().namespace(), packet.channel().key()));
        container.getModifier().write(1, friendlyByteBuf);
        return container;

    }
}
