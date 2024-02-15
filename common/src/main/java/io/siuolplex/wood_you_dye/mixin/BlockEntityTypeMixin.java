package io.siuolplex.wood_you_dye.mixin;

import io.siuolplex.wood_you_dye.block.sign.WoodYouDyeSign;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Code is based on Terraform
// Found here: https://github.com/TerraformersMC/Terraform/blob/1.19.4/terraform-wood-api-v1/src/main/java/com/terraformersmc/terraform/sign/mixin/MixinBlockEntityType.java
@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
        Block block = state.getBlock();

        if (block instanceof WoodYouDyeSign) {
            if (BlockEntityType.HANGING_SIGN.equals(this)) {
                if (!(block instanceof HangingSignBlock || block instanceof WallHangingSignBlock)) {
                    return;
                }
            } else if (!BlockEntityType.SIGN.equals(this)) {
                return;
            }

            info.setReturnValue(true);
        }
    }
}
