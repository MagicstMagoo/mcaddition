package cn.magicst.mcaddition;

import cn.magicst.mcaddition.network.PcaSyncProtocol;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

public class PcaMod
{
    @Nullable
    public static MinecraftServer server = null;

    public static void init(MinecraftServer server) {
        PcaSyncProtocol.init();
        PcaMod.server = server;
    }
}
