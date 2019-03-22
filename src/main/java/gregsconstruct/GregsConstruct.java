package gregsconstruct;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;

@Mod(modid = GregsConstruct.MODID,
        name = GregsConstruct.NAME,
        version = GregsConstruct.VERSION,
        dependencies = "required-after:gregtech;required-after:tconstruct"
)
public class GregsConstruct {
    public static final String MODID = "gtconstruct";
    public static final String NAME = "Greg's Construct";
    public static final String VERSION = "@VERSION@";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        Materials.preInit();
    }

    @Mod.EventBusSubscriber
    public static class events {
        @SubscribeEvent(priority = EventPriority.LOW)
        public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
            GtRecipes.init();
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void smeltingRemoval(TinkerRegisterEvent.MeltingRegisterEvent event) {
            for (Material mat : Material.MATERIAL_REGISTRY)
                if (mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0 && (matches(event, OrePrefix.ore, mat) || matches(event, OrePrefix.dust, mat) || matches(event, OrePrefix.dustSmall, mat) || matches(event, OrePrefix.dustTiny, mat)))
                    event.setCanceled(true);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void alloyRemoval(TinkerRegisterEvent.AlloyRegisterEvent event) {
            if (event.getRecipe().getResult() == gregtech.api.unification.material.Materials.Brass.getFluid(3))
                event.setCanceled(true);
        }

        private static boolean matches(TinkerRegisterEvent.MeltingRegisterEvent e, OrePrefix prefix, Material mat) {
            return e.getRecipe().input.matches(NonNullList.withSize(1, OreDictUnifier.get(prefix, mat))).isPresent();
        }
    }
}
