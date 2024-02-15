package io.siuolplex.wood_you_dye.registry;

import dev.architectury.registry.registries.Registrar;
import io.siuolplex.wood_you_dye.WoodYouDyeMain;
import io.siuolplex.wood_you_dye.block.*;
import io.siuolplex.wood_you_dye.block.sign.WoodYouDyeHangingSignBlock;
import io.siuolplex.wood_you_dye.block.sign.WoodYouDyeHangingWallSignBlock;
import io.siuolplex.wood_you_dye.block.sign.WoodYouDyeSignBlock;
import io.siuolplex.wood_you_dye.block.sign.WoodYouDyeWallSignBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.function.TriFunction;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static io.siuolplex.wood_you_dye.WoodYouDyeMain.MOD_ID;
import static io.siuolplex.wood_you_dye.registry.WoodYouDyeSignTypes.WOOD_TYPES;

public class WoodYouDyeRegistrar {
    public record BlockWithItem(Block block, Item item) {
        BlockWithItem(Block block, Item.Settings settings) {
            this(block, new BlockItem(block,settings));
        }
    }
    public record SignBlocksWithItem(Block sign, Block wallSign, Item signItem) {
        public static SignBlocksWithItem createSignBlocksWithItem(BiFunction<AbstractBlock.Settings, WoodType, Block> signFactory, BiFunction<AbstractBlock.Settings, WoodType, Block> wallSignFactory, TriFunction<Block, Block, Item.Settings, Item> signItemFactory, WoodType type, AbstractBlock.Settings blockSettings, Item.Settings itemSettings) {
            Block sign = signFactory.apply(blockSettings, type);
            Block wallSign = wallSignFactory.apply(blockSettings, type);
            Item signItem = signItemFactory.apply(sign, wallSign, itemSettings);
            return new SignBlocksWithItem(sign,wallSign,signItem);
        }
    }
    public record BlockSet(
            BlockWithItem planks,
            BlockWithItem slab,
            BlockWithItem stairs,
            BlockWithItem fence,
            BlockWithItem fenceGate,
            BlockWithItem pressurePlate,
            BlockWithItem button,
            BlockWithItem door,
            BlockWithItem trapDoor,
            SignBlocksWithItem signs,
            SignBlocksWithItem hangingSigns
    ) {
        public static BlockSet createWoodenSet(String name, WoodType type, MapColor color) {
            Block plankBlock = new Block(commonSettings().mapColor(color));
            return new BlockSet(
                    new BlockWithItem(plankBlock,sharedItemSettings()),
                    new BlockWithItem(new SlabBlock(commonSettings().mapColor(color)),sharedItemSettings()),
                    new BlockWithItem(new WoodYouDyeStairBlock(plankBlock.getDefaultState(),commonSettings().mapColor(color)),sharedItemSettings()),
                    new BlockWithItem(new FenceBlock(commonSettings().mapColor(color)),sharedItemSettings()),
                    new BlockWithItem(new WoodYouDyeFenceGateBlock(commonSettings().mapColor(color)),sharedItemSettings()),
                    new BlockWithItem(new WoodYouDyePressurePlateBlock(commonSettings().mapColor(color)),sharedItemSettings()),
                    new BlockWithItem(new WoodYouDyeWoodenButtonBlock(commonSettings().mapColor(color)),sharedItemSettings()),
                    new BlockWithItem(new WoodYouDyeDoorBlock(commonSettings().mapColor(color)),sharedItemSettings()),
                    new BlockWithItem(new WoodYouDyeTrapdoorBlock(commonSettings().mapColor(color)),sharedItemSettings()),
                    SignBlocksWithItem.createSignBlocksWithItem(
                            WoodYouDyeSignBlock::new,
                            WoodYouDyeWallSignBlock::new,
                            (b1,b2,s)->new SignItem(s,b1,b2),
                            type,
                            commonSettings(),
                            sharedItemSettings()
                    ),
                    SignBlocksWithItem.createSignBlocksWithItem(
                            WoodYouDyeHangingSignBlock::new,
                            WoodYouDyeHangingWallSignBlock::new,
                            HangingSignItem::new,
                            type,
                            commonSettings(),
                            sharedItemSettings()
                    )
            );
        }
    }

    public static Registrar<Block> BLOCKS_REGISTRAR = WoodYouDyeMain.MANAGER.get().get(RegistryKeys.BLOCK);
    public static Registrar<Item> ITEMS_REGISTRAR = WoodYouDyeMain.MANAGER.get().get(RegistryKeys.ITEM);
    static Map<String, BlockSet> BLOCK_SETS = WOOD_TYPES.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->BlockSet.createWoodenSet(e.getKey(),e.getValue().type(),e.getValue().color().getMapColor())));

    public static void register(String id, Block block) {
        BLOCKS_REGISTRAR.register(new Identifier(MOD_ID,id),()->block);
    }
    public static void register(String id, Item item) {
        ITEMS_REGISTRAR.register(new Identifier(MOD_ID,id),()->item);
    }
    public static void register(String id, BlockWithItem blockWithItem) {
        register(id, blockWithItem.block());
        register(id, blockWithItem.item());
    }
    public static void register(String id, SignBlocksWithItem signBlocksWithItem) {
        register(id+"_sign", signBlocksWithItem.sign());
        register(id+"_wall_sign", signBlocksWithItem.wallSign());
        register(id+"_sign", signBlocksWithItem.signItem());
    }
    public static void init() {
        BLOCK_SETS.forEach((name,set)->{
            String prefix = name+"_plank";
            register(prefix+"s", set.planks());
            register(prefix+"_slab", set.slab());
            register(prefix+"_stairs", set.stairs());
            register(prefix+"_fence", set.fence());
            register(prefix+"_fence_gate", set.fenceGate());
            register(prefix+"_pressure_plate", set.pressurePlate());
            register(prefix+"_button", set.button());
            register(prefix+"_door", set.door());
            register(prefix+"_trapdoor", set.trapDoor());
            register(prefix+"_hanging", set.hangingSigns());
            register(prefix, set.signs());
        });
    }

    public static AbstractBlock.Settings commonSettings() {
        return AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD).instrument(Instrument.BASS).strength(3.0f).burnable();
    }
    public static Item.Settings sharedItemSettings() {
        return new Item.Settings();
    }
}
