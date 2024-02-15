package io.siuolplex.wood_you_dye.fabric;

import io.siuolplex.wood_you_dye.block.WoodYouDyeDoorBlock;
import io.siuolplex.wood_you_dye.block.WoodYouDyeTrapdoorBlock;
import io.siuolplex.wood_you_dye.registry.WoodYouDyeRegistrar;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class WoodYouDyeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WoodYouDyeRegistrar.BLOCKS_REGISTRAR.forEach(block->{
            if (block instanceof WoodYouDyeTrapdoorBlock) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
            }else if (block instanceof WoodYouDyeDoorBlock) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
            }
        });
    }
}
