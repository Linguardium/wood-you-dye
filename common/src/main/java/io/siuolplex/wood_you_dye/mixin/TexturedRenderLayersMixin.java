package io.siuolplex.wood_you_dye.mixin;

import io.siuolplex.wood_you_dye.util.SpriteIdentifierRegistry;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.screen.StonecutterScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;


@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
    // Is this even used what
    /*@Inject(method = "addDefaultTextures", at = @At("RETURN"))
    private static void injectSoulIceSigns(Consumer<Material> adder, CallbackInfo ci) {
        for(Material identifier: SpriteIdentifierRegistry.INSTANCE.getIdentifiers()) {
            adder.accept(identifier);
        }
    }*/
}