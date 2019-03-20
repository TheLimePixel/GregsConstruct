package gregsconstruct;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.shared.TinkerCommons;

@Mod.EventBusSubscriber
public class Removal {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void smeltingRemoval(TinkerRegisterEvent.MeltingRegisterEvent event) {
        for (Material mat : Material.MATERIAL_REGISTRY)
            if (mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0 && (matches(event, OrePrefix.ore, mat) || matches(event, OrePrefix.dust, mat) || matches(event, OrePrefix.dustSmall, mat) || matches(event, OrePrefix.dustTiny, mat)))
                event.setCanceled(true);
        if (event.getRecipe().output == Materials.Glass.getFluid(1000) || event.getRecipe().output == Materials.Glass.getFluid(375))
            event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void alloyRemoval(TinkerRegisterEvent.AlloyRegisterEvent event) {
        if (event.getRecipe().getResult() == Materials.Brass.getFluid(3))
            event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void basinCastRemoval(TinkerRegisterEvent.BasinCastingRegisterEvent event) {
        if (event.getRecipe().getResult(null, Materials.Glass.getMaterialFluid()) == new ItemStack(TinkerCommons.blockClearGlass))
            event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void tableCastRemoval(TinkerRegisterEvent.TableCastingRegisterEvent event) {
        if (event.getRecipe().getResult(null, Materials.Glass.getMaterialFluid()) == new ItemStack(Blocks.GLASS_PANE))
            event.setCanceled(true);
    }

    private static boolean matches(TinkerRegisterEvent.MeltingRegisterEvent e, OrePrefix prefix, Material mat) {
        return e.getRecipe().input.matches(NonNullList.withSize(1, OreDictUnifier.get(prefix, mat))).isPresent();
    }
}