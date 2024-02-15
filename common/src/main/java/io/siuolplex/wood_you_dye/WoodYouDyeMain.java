package io.siuolplex.wood_you_dye;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import io.siuolplex.wood_you_dye.registry.WoodYouDyeRegistrar;

import java.util.function.Supplier;

public class WoodYouDyeMain {
    public static final String MOD_ID = "wood_you_dye";
    public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));
    public static void init() {
        WoodYouDyeRegistrar.init();
    }
}
