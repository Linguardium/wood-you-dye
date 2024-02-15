package io.siuolplex.wood_you_dye.mixin;

import net.minecraft.block.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WoodType.class)
public interface WoodTypeAccessor {
    @Invoker("register")
    public static WoodType WoodYouDye$register(WoodType type) { throw new RuntimeException("Mixin failed to apply"); }

}
