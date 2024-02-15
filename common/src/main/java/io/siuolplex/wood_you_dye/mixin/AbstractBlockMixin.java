package io.siuolplex.wood_you_dye.mixin;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

import static io.siuolplex.wood_you_dye.WoodYouDyeMain.MOD_ID;

// Based on similar code by supersaiyansubtlety, made for Defaulted Drops, which is licensed under MIT, and found here: https://gitlab.com/supersaiyansubtlety-group/minecraft-mods/defaulted_drops/-/tree/master/
//TODO: REPLACE WITH DATAGEN

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Shadow
    public abstract Item asItem();

    @Inject(method = "getDroppedStacks", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "RETURN"))
    private void addSelfIfNoLootTable(BlockState state, LootContextParameterSet.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir, Identifier identifier) {
        if (state.getBlock().getLootTableId().getNamespace().equals(MOD_ID)) {
            if (state.getBlock() instanceof SlabBlock && state.get(SlabBlock.TYPE).equals(SlabType.DOUBLE)) {
                cir.setReturnValue(List.of(new ItemStack(this.asItem()), new ItemStack(this.asItem())));
            } else if (state.getBlock() instanceof DoorBlock && state.get(DoorBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
                cir.setReturnValue(List.of(Items.AIR.getDefaultStack()));
            } else if (state.getBlock() instanceof AbstractSignBlock sign) {
                cir.setReturnValue(List.of(new ItemStack(sign.asItem())));
            } else {
                cir.setReturnValue(List.of(new ItemStack(this.asItem())));
            }
        }
    }
}

