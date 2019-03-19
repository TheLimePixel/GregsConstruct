package gregsconstruct;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

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
        MinecraftForge.EVENT_BUS.register(new Removal());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Materials.postInit();
    }
}
