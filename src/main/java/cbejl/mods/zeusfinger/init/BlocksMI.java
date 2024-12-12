package cbejl.mods.zeusfinger.init;

import cbejl.mods.zeusfinger.ZeusFinger;
import cbejl.mods.zeusfinger.block.LightningChargerBlock;
import cbejl.mods.zeusfinger.block.LightningChargerBlockTile;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.hammerlib.api.forge.BlockAPI;

@SimplyRegister
public interface BlocksMI {
    @RegistryName("lightning_charger_block")
    LightningChargerBlock LIGHTNING_CHARGER_BLOCK = ZeusFinger.MOD_TAB.add(new LightningChargerBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));

    @RegistryName("lightning_charger_tile")
    BlockEntityType<LightningChargerBlockTile> LIGHTNING_CHARGER_BLOCK_TILE = BlockAPI.createBlockEntityType(LightningChargerBlockTile::new, LIGHTNING_CHARGER_BLOCK);
}