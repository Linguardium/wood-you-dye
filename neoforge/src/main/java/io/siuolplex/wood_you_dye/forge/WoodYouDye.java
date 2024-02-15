package io.siuolplex.wood_you_dye.forge;

import com.mojang.datafixers.util.Either;
import io.siuolplex.wood_you_dye.WoodYouDyeMain;
import io.siuolplex.wood_you_dye.registry.WoodYouDyeBlocks;
import io.siuolplex.wood_you_dye.registry.WoodYouDyeItems;
import io.siuolplex.wood_you_dye.util.Loader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;

import java.util.LinkedHashMap;
import java.util.Map;

@Mod(WoodYouDye.ID)
public class WoodYouDye {
    public static final String ID = "wood_you_dye";

    public static final Loader MODLOADER = new ForgeLoader();

    public static Map<String, Block> blockRegistry = new LinkedHashMap<>();
    public static Map<String, Item> itemRegistry = new LinkedHashMap<>();


    final ModLoadingContext modLoadingContext = ModLoadingContext.get();
    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public WoodYouDye() {
        WoodYouDyeMain.init(MODLOADER);
    }

    public static final class ForgeLoader implements Loader {
        @Override
        public String getName() {
            return "forge";
        }

        @Override
        public boolean getIsClient() {
            return FMLLoader.getDist() == Dist.CLIENT;
        }

        @Override
        public <T> T register(T registeredObject, String id) {
            if (registeredObject instanceof Block block) {
                blockRegistry.put(id, block);
            } else if (registeredObject instanceof Item item) {
                itemRegistry.put(id, item);
            }
            return registeredObject;
        }
    }
}
