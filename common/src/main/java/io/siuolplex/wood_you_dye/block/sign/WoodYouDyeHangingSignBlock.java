package io.siuolplex.wood_you_dye.block.sign;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

// Code is based on Terraform
// Original found here: https://github.com/TerraformersMC/Terraform/blob/1.19.4/terraform-wood-api-v1/src/main/java/com/terraformersmc/terraform/sign/block/TerraformSignBlock.java
public class WoodYouDyeHangingSignBlock extends HangingSignBlock implements WoodYouDyeHangingSign {
    private final Identifier guiTexture;
    private final Identifier texture;

    public WoodYouDyeHangingSignBlock(AbstractBlock.Settings settings, WoodType type) {
        super(type, settings);
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
