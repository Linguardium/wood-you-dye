package io.siuolplex.wood_you_dye.block.sign;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

public class WoodYouDyeHangingWallSignBlock extends WallHangingSignBlock implements WoodYouDyeHangingSign {
    private final Identifier guiTexture;
    private final Identifier texture;

    public WoodYouDyeHangingWallSignBlock(AbstractBlock.Settings settings, WoodType type) {
        super(settings, type);
        this.texture = new Identifier("wood_you_dye", "entity/signs/hanging/" + type.name());
        this.guiTexture = new Identifier("wood_you_dye", "textures/gui/hanging_sign/" + type.name());
    }

    @Override
    public Identifier getGuiTexture() {
        return guiTexture;
    }

    @Override
    public Identifier getTexture() {
        return texture;
    }
}
