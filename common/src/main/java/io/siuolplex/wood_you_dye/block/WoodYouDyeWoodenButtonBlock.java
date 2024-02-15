package io.siuolplex.wood_you_dye.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;

public class WoodYouDyeWoodenButtonBlock extends ButtonBlock {
    public WoodYouDyeWoodenButtonBlock(Settings settings) {
        super(settings.noCollision(), BlockSetType.OAK, 20, true);
    }
}
