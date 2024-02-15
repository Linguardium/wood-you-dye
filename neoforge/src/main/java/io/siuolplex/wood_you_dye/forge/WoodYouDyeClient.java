package io.siuolplex.wood_you_dye.forge;

import io.siuolplex.wood_you_dye.registry.WoodYouDyeBlocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.RenderTypeGroup;
import net.minecraftforge.client.RenderTypeHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WoodYouDye.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WoodYouDyeClient {
    @SubscribeEvent
    public static void onInitializeClient(final FMLClientSetupEvent event) {
        for (DyeColor color : DyeColor.values()) {
            RenderLayers.setRenderLayer(Registries.BLOCK.get(new Identifier("wood_you_dye", color.toString().toLowerCase() + "_plank_door")), RenderLayer.getTranslucent());
            RenderLayers.setRenderLayer(Registries.BLOCK.get(new Identifier("wood_you_dye", color.toString().toLowerCase() + "_plank_trapdoor")), RenderLayer.getCutout());
        }
    }
}
