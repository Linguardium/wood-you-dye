package io.siuolplex.wood_you_dye.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.DoorBlock;

public class WoodYouDyeDoorBlock extends DoorBlock {
    public WoodYouDyeDoorBlock(Settings settings) {
        super(BlockSetType.OAK, settings.nonOpaque());
    }
}
