package tfar.harvestcrafttweaker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = HarvestCraftTweaker.MODID, name = HarvestCraftTweaker.NAME, version = HarvestCraftTweaker.VERSION)
public class HarvestCraftTweaker
{
    public static final String MODID = "harvestcrafttweaker";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void post(FMLPostInitializationEvent event) {
    }
}
