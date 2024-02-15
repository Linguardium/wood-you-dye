package io.siuolplex.wood_you_dye.forge;

import io.siuolplex.wood_you_dye.WoodYouDyeMain;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import static io.siuolplex.wood_you_dye.WoodYouDyeMain.MOD_ID;

@Mod(MOD_ID)
public class WoodYouDye {

    final IEventBus modEventBus;

    public WoodYouDye(IEventBus eventBus) {
        this.modEventBus = eventBus;
        WoodYouDyeMain.init();
    }
}
