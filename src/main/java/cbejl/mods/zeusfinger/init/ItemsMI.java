package cbejl.mods.zeusfinger.init;

import cbejl.mods.zeusfinger.ZeusFinger;
import cbejl.mods.zeusfinger.item.ZeusFingerItem;
import net.minecraft.world.item.Item;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ItemsMI
{
    @RegistryName("zeus_finger")
    ZeusFingerItem ZEUS_FINGER = ZeusFinger.MOD_TAB.add(new ZeusFingerItem(
            new Item.Properties().defaultDurability(21)));
}