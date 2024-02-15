package io.siuolplex.wood_you_dye.fabric;

import io.siuolplex.wood_you_dye.WoodYouDyeMain;
import io.siuolplex.wood_you_dye.registry.WoodYouDyeItems;
import io.siuolplex.wood_you_dye.util.Loader;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.nio.file.Path;
import java.util.function.Function;

public class WoodYouDye implements ModInitializer {
    public static final Loader MODLOADER = new WYDFabricLoader();

    @Override
    public void onInitialize() {
        WoodYouDyeMain.init(MODLOADER);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            for (Item item : WoodYouDyeItems.itemGroupHolder) {
                content.addBefore(Items.STONE.getDefaultStack(), item.getDefaultStack());
            }
        });
    }

    public static class WYDFabricLoader implements Loader {
        @Override
        public String getName() {
            return "fabric";
        }

        @Override
        public boolean getIsClient() {
            return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
        }

        @Override
        public <T> T register(T registeredObject, String id) {
            if (registeredObject instanceof Block block) {
                return (T) Registry.register(Registries.BLOCK, new Identifier("wood_you_dye", id), block);
            } else if (registeredObject instanceof Item item) {
                return (T) Registry.register(Registries.ITEM, new Identifier("wood_you_dye", id), item);
            }
            else return null;
        }
    }
}
