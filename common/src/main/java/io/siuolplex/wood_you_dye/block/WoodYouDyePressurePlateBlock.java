package io.siuolplex.wood_you_dye.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.PressurePlateBlock;

public class WoodYouDyePressurePlateBlock extends PressurePlateBlock {
    public WoodYouDyePressurePlateBlock(Settings settings) {
        super(BlockSetType.OAK, settings.noCollision());
    }
}
