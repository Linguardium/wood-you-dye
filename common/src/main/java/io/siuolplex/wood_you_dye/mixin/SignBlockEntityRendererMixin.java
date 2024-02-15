package io.siuolplex.wood_you_dye.mixin;

import io.siuolplex.wood_you_dye.block.sign.WoodYouDyeSign;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// Code is based on Terraform
// Found here: https://github.com/TerraformersMC/Terraform/blob/1.19.4/terraform-wood-api-v1/src/main/java/com/terraformersmc/terraform/sign/mixin/MixinSignBlockEntityRenderer.java
@Mixin(SignBlockEntityRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class SignBlockEntityRendererMixin {
    @Unique
    protected SignBlockEntity soulIce$renderedBlockEntity;

    @Inject(method = "Lnet/minecraft/client/render/block/entity/SignBlockEntityRenderer;render(Lnet/minecraft/block/entity/SignBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "HEAD"))
    private void setRenderedToSign(SignBlockEntity signBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci) {
        this.soulIce$renderedBlockEntity = signBlockEntity;
    }

    /*@Redirect(method = "method_32158", at = @At(value = "NEW", target = "(Lnet/minecraft/client/model/ModelPart;)Lnet/minecraft/client/render/block/entity/SignBlockEntityRenderer$SignModel;"))
    private static SignBlockEntityRenderer.SignModel motherfucker(ModelPart modelPart, BlockEntityRendererFactory.Context ctx, SignType signType) {
        if (signType.setType().name().equals("wyd_wood")) return (new SignBlockEntityRenderer.SignModel(ctx.getLayerModelPart(new EntityModelLayer(new Identifier("wood_you_dye", "sign/" + signType.getName()), "main"))));
        else return new SignBlockEntityRenderer.SignModel(ctx.getLayerModelPart(EntityModelLayers.createSign(signType)));
    }*/

    @Inject(method = "getSignTexture", at = @At("HEAD"), cancellable = true)
    private void getSignTextureId(CallbackInfoReturnable<SpriteIdentifier> ci) {
        if (this.soulIce$renderedBlockEntity != null) {
            if (this.soulIce$renderedBlockEntity.getCachedState().getBlock() instanceof WoodYouDyeSign sign) {
                ci.setReturnValue(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, sign.getTexture()));
            }
        }
    }
}
