package io.siuolplex.wood_you_dye.registry;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.util.DyeColor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WoodYouDyeSignTypes {
    public static record DyedWoodType(WoodType type, DyeColor color) {};
    public static final Map<String, DyedWoodType> WOOD_TYPES = Arrays.stream(DyeColor.values()).map(dyeColor-> {
        String name = "wyd_" + dyeColor.asString();
        return new DyedWoodType(new WoodType(name, new BlockSetType(name)),dyeColor);
    }).collect(Collectors.toMap(t->t.type.name(), t->t));

//
//    private static WoodType woodType(String ...name) {
//
//        return new WoodType(name,new BlockSetType(name));
//    }
//    public static WoodType RED_SIGNS = new WoodType("red", new BlockSetType("wyd_wood"));
//    public static WoodType ORANGE_SIGNS = new WoodType("orange", new BlockSetType("wyd_wood"));
//    public static WoodType YELLOW_SIGNS = new WoodType("yellow", new BlockSetType("wyd_wood"));
//    public static WoodType LIME_SIGNS = new WoodType("lime", new BlockSetType("wyd_wood"));
//    public static WoodType GREEN_SIGNS = new WoodType("green", new BlockSetType("wyd_wood"));
//    public static WoodType BLUE_SIGNS = new WoodType("blue", new BlockSetType("wyd_wood"));
//    public static WoodType CYAN_SIGNS = new WoodType("cyan", new BlockSetType("wyd_wood"));
//    public static WoodType LIGHT_BLUE_SIGNS = new WoodType("light_blue", new BlockSetType("wyd_wood"));
//    public static WoodType PURPLE_SIGNS = new WoodType("purple", new BlockSetType("wyd_wood"));
//    public static WoodType PINK_SIGNS = new WoodType("pink", new BlockSetType("wyd_wood"));
//    public static WoodType MAGENTA_SIGNS = new WoodType("magenta", new BlockSetType("wyd_wood"));
//    public static WoodType BROWN_SIGNS = new WoodType("brown", new BlockSetType("wyd_wood"));
//    public static WoodType WHITE_SIGNS = new WoodType("white", new BlockSetType("wyd_wood"));
//    public static WoodType LIGHT_GRAY_SIGNS = new WoodType("light_gray", new BlockSetType("wyd_wood"));
//    public static WoodType GRAY_SIGNS = new WoodType("gray", new BlockSetType("wyd_wood"));
//    public static WoodType BLACK_SIGNS = new WoodType("black", new BlockSetType("wyd_wood"));
//



    public static void init(Consumer<WoodType> registrar) {
        WOOD_TYPES.forEach((name,type)->registrar.accept(type.type()));
    }
}
