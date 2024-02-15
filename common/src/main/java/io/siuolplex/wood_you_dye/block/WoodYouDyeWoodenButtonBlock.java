package io.siuolplex.wood_you_dye.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;

public class WoodYouDyeWoodenButtonBlock extends ButtonBlock {
    public WoodYouDyeWoodenButtonBlock(Settings settings) {
        super(BlockSetType.OAK, 20, settings.noCollision());
    }
}
