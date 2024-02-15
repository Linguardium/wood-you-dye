package io.siuolplex.wood_you_dye.mixin;

import io.siuolplex.wood_you_dye.block.sign.WoodYouDyeHangingSign;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.ingame.HangingSignEditScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


// Code is based on Terraform
// Found here: https://github.com/TerraformersMC/Terraform/blob/1.19.4/terraform-wood-api-v1/src/main/java/com/terraformersmc/terraform/sign/mixin/MixinHangingSignEditScreen.java
@Mixin(HangingSignEditScreen.class)
@Environment(EnvType.CLIENT)
public class HangingSignEditScreenMixin {

    @Final
    @Mutable
    @Shadow private Identifier texture;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void initSignTextureId(SignBlockEntity signBlockEntity, boolean bl, boolean bl2, CallbackInfo ci) {
        if (signBlockEntity.getCachedState().getBlock() instanceof WoodYouDyeHangingSign sign) {
            Identifier guiTexture = sign.getGuiTexture();
            this.texture = new Identifier(guiTexture.getNamespace(), guiTexture.getPath() + ".png");
        }
    }
}
