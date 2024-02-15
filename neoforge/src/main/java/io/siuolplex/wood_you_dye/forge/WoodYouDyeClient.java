package io.siuolplex.wood_you_dye.forge;

import io.siuolplex.wood_you_dye.block.WoodYouDyeDoorBlock;
import io.siuolplex.wood_you_dye.block.WoodYouDyeTrapdoorBlock;
import io.siuolplex.wood_you_dye.registry.WoodYouDyeRegistrar;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import static io.siuolplex.wood_you_dye.WoodYouDyeMain.MOD_ID;


@Mod.EventBusSubscriber(modid=MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WoodYouDyeClient {
    @SubscribeEvent
    public static void onInitializeClient(final FMLClientSetupEvent event) {
        WoodYouDyeRegistrar.BLOCKS_REGISTRAR.forEach(block->{
            if (block instanceof WoodYouDyeTrapdoorBlock) {
                RenderLayers.setRenderLayer(block, RenderLayer.getCutout());
            }else if (block instanceof WoodYouDyeDoorBlock) {
                RenderLayers.setRenderLayer(block, RenderLayer.getTranslucent());
            }
        });
    }
}
