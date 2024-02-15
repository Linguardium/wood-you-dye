package io.siuolplex.wood_you_dye.forge;

import io.siuolplex.wood_you_dye.registry.WoodYouDyeRegistrar;
import net.minecraft.item.ItemGroups;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import static io.siuolplex.wood_you_dye.WoodYouDyeMain.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoodYouDyeItemGroup {
    @SubscribeEvent
    public static void addToItemGroup(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ItemGroups.BUILDING_BLOCKS) {
            WoodYouDyeRegistrar.ITEMS_REGISTRAR.forEach(event::add);
        }
    }
}
