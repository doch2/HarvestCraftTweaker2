package tfar.harvestcrafttweaker.mixin;

import com.pam.harvestcraft.tileentities.TileEntityWaterTrap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import tfar.harvestcrafttweaker.WaterTrapRecipes;

@Mixin(value = TileEntityWaterTrap.class,remap = false)
public abstract class TileEntityWaterTrapMixin {

	@Shadow @Final private ItemStackHandler itemstackhandler;

	@Shadow protected abstract int getRunTime();

	/**
	 * @author Tfar
	 * @reason bad code, redirected to use a recipe system instead of hardcoded switch statements
	 */
	@Overwrite(remap = false)
	public ItemStack getComb(){
		return WaterTrapRecipes.getResult(itemstackhandler.getStackInSlot(18));
	}

	/**
	 * @author Tfar
	 * @reason bad code, hardcoded to specific items, rerouted to check recipe system for outputs
	 */
	@Overwrite(remap = false)
	private boolean canRun(){
		ItemStack stack = itemstackhandler.getStackInSlot(18);
		return !stack.isEmpty() && WaterTrapRecipes.isValidInput(stack);
	}

	/**
	 * @author Tfar
	 * @reason bad code, hardcoded to return 0 for anything other than a couple items
	 */
	@Overwrite(remap = false)
	private int getRunTime(ItemStack stack){
		return stack.isEmpty() ? 0 : getRunTime();
	}
}
