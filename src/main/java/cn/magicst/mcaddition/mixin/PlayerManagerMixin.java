package cn.magicst.mcaddition.mixin;

import cn.magicst.mcaddition.fakefqapi.PacketSender;
import cn.magicst.mcaddition.network.PcaSyncProtocol;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
	@Shadow
	@Final
	private MinecraftServer server;
	// quilt-fabric api ServerPlayConnectionEvents.JOIN
	@Inject(method = "onPlayerConnect", at = @At("TAIL"))
	private void handleDisconnection(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
		PcaSyncProtocol.onJoin(player.networkHandler, new PacketSender(), this.server);
	}
}
