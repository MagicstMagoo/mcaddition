package cn.magicst.mcaddition.mixin.rule.pcaSyncProtocol.block;

import cn.magicst.mcaddition.Main;
import cn.magicst.mcaddition.PcaSettings;
import cn.magicst.mcaddition.network.PcaSyncProtocol;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BarrelBlockEntity.class)
public abstract class MixinBarrelBlockEntity extends LootableContainerBlockEntity {

    protected MixinBarrelBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public void markDirty() {
        super.markDirty();

        if (PcaSettings.pcaSyncProtocol && PcaSyncProtocol.syncBlockEntityToClient(this)) {
            Main.LOGGER.debug("update BarrelBlockEntity: {}", this.pos);
        }
    }
}
