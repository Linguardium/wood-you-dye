package io.siuolplex.wood_you_dye.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import io.siuolplex.wood_you_dye.block.sign.WoodYouDyeSign;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SignBlockEntityRenderer.class, HangingSignBlockEntityRenderer.class})
@Environment(EnvType.CLIENT)
public abstract class SignBlockEntityRendererMixin {

    @Inject(method = {"render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V","render(Lnet/minecraft/block/entity/SignBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V"}, at = @At(value = "HEAD"), require = 0)
    private void setRenderedToSign(@Coerce BlockEntity signBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci, @Share("signEntity") LocalRef<BlockEntity> signEntity) {
        signEntity.set(signBlockEntity);
    }

    @WrapOperation(method = {"getTextureId"}, at={@At(value="INVOKE",target = "Lnet/minecraft/client/render/TexturedRenderLayers;getSignTextureId(Lnet/minecraft/block/WoodType;)Lnet/minecraft/client/util/SpriteIdentifier;"),@At(value= "INVOKE",target = "Lnet/minecraft/client/render/TexturedRenderLayers;getHangingSignTextureId(Lnet/minecraft/block/WoodType;)Lnet/minecraft/client/util/SpriteIdentifier;")}, require=0)
    private SpriteIdentifier getSignTextureId(WoodType signType, Operation<SpriteIdentifier> original, @Share("signEntity") LocalRef<BlockEntity> signEntity) {
        if (signEntity.get() instanceof WoodYouDyeSign wydSign) {
            return new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, wydSign.getTexture());
        }
        return original.call(signType);
    }
}
