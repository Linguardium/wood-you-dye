package io.siuolplex.wood_you_dye.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.TrapdoorBlock;

public class WoodYouDyeTrapdoorBlock extends TrapdoorBlock {
    public WoodYouDyeTrapdoorBlock(Settings settings) {
        super(BlockSetType.OAK, settings.nonOpaque());
    }
}
