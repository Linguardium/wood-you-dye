package io.siuolplex.wood_you_dye.mixin;

import io.siuolplex.wood_you_dye.WoodYouDyeMain;
import io.siuolplex.wood_you_dye.registry.WoodYouDyeSignTypes;
import net.minecraft.block.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WoodType.class)
public abstract class SignTypeMixin {
    @Shadow
    private static WoodType register(WoodType type) {
        return null;
    }

    @Inject(method = "<clinit>", at = @At(value = "RETURN"))
    private static void a(CallbackInfo ci) {
        WoodYouDyeSignTypes.init(woodType->register(woodType));
//        register(WoodYouDyeSignTypes.RED_SIGNS);
//        register(WoodYouDyeSignTypes.ORANGE_SIGNS);
//        register(WoodYouDyeSignTypes.YELLOW_SIGNS);
//        register(WoodYouDyeSignTypes.LIME_SIGNS);
//        register(WoodYouDyeSignTypes.GREEN_SIGNS);
//        register(WoodYouDyeSignTypes.BLUE_SIGNS);
//        register(WoodYouDyeSignTypes.CYAN_SIGNS);
//        register(WoodYouDyeSignTypes.LIGHT_BLUE_SIGNS);
//        register(WoodYouDyeSignTypes.PURPLE_SIGNS);
//        register(WoodYouDyeSignTypes.PINK_SIGNS);
//        register(WoodYouDyeSignTypes.MAGENTA_SIGNS);
//        register(WoodYouDyeSignTypes.BROWN_SIGNS);
//        register(WoodYouDyeSignTypes.WHITE_SIGNS);
//        register(WoodYouDyeSignTypes.LIGHT_GRAY_SIGNS);
//        register(WoodYouDyeSignTypes.GRAY_SIGNS);
//        register(WoodYouDyeSignTypes.BLACK_SIGNS);

    }

}
