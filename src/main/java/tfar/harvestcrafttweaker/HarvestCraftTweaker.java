package tfar.harvestcrafttweaker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = HarvestCraftTweaker.MODID, name = HarvestCraftTweaker.NAME, version = HarvestCraftTweaker.VERSION,
        dependencies = "required:harvestcraft;")
public class HarvestCraftTweaker
{
    public static final String MODID = "harvestcrafttweaker";
    public static final String NAME = "HarvestCraftTweaker";
    public static final String VERSION = "@VERSION@";

    private static Logger logger;

    @EventHandler
    public void post(FMLPostInitializationEvent event) {
        if (Methods.clearmarket) {
            ReflectionHacks.clearAllMarket();
        } else {
            Methods.marketOutputsToRemove.forEach(ReflectionHacks::removeMarketTrade);
        }
        Methods.marketToAdd.forEach(ReflectionHacks::addMarketTrade);
    }
}
