package tfar.harvestcrafttweaker;

import com.google.common.collect.Lists;
import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WaterTrapRecipes {
	public static final Map<Ingredient, List<ItemStack>> waterTrapRecipes = new HashMap<>();

	public static final Random rand = new Random();

	public static void addTrapRecipe(Ingredient input, List<ItemStack> outputs) {
		waterTrapRecipes.put(input, outputs);
	}

	public static ItemStack getResult(ItemStack input) {
		return waterTrapRecipes.entrySet().stream()
						.filter(entry -> entry.getKey().test(input))
						.map(Map.Entry::getValue).findFirst()
						.map(stacks -> stacks.get(rand.nextInt(stacks.size()))).orElse(ItemStack.EMPTY);
	}

	public static boolean isValidInput(ItemStack input){
		return waterTrapRecipes.entrySet().stream().anyMatch(entry -> entry.getKey().test(input));
	}

	static {
		addTrapRecipe(Ingredient.fromStacks(new ItemStack(ItemRegistry.fishtrapbaitItem)),
						Lists.newArrayList(
			 new ItemStack(Items.FISH),
			 new ItemStack(Items.FISH, 1, 1),
			 new ItemStack(Items.FISH, 1, 2),
			 new ItemStack(Items.FISH, 1, 3),
			 new ItemStack(ItemRegistry.anchovyrawItem),
			 new ItemStack(ItemRegistry.bassrawItem),
			 new ItemStack(ItemRegistry.carprawItem),
			 new ItemStack(ItemRegistry.catfishrawItem),
			 new ItemStack(ItemRegistry.charrrawItem),
			 new ItemStack(ItemRegistry.clamrawItem),
			 new ItemStack(ItemRegistry.crabrawItem),
			 new ItemStack(ItemRegistry.crayfishrawItem),
			 new ItemStack(ItemRegistry.eelrawItem),
			 new ItemStack(ItemRegistry.frograwItem),
			 new ItemStack(ItemRegistry.grouperrawItem),
			 new ItemStack(ItemRegistry.herringrawItem),
			 new ItemStack(ItemRegistry.jellyfishrawItem),
			 new ItemStack(ItemRegistry.mudfishrawItem),
			 new ItemStack(ItemRegistry.octopusrawItem),
			 new ItemStack(ItemRegistry.perchrawItem),
			 new ItemStack(ItemRegistry.scalloprawItem),
			 new ItemStack(ItemRegistry.shrimprawItem),
			 new ItemStack(ItemRegistry.snailrawItem),
			 new ItemStack(ItemRegistry.snapperrawItem),
			 new ItemStack(ItemRegistry.tilapiarawItem),
			 new ItemStack(ItemRegistry.troutrawItem),
			 new ItemStack(ItemRegistry.tunarawItem),
			 new ItemStack(ItemRegistry.turtlerawItem),
			 new ItemStack(ItemRegistry.walleyerawItem),
			 new ItemStack(Items.FISH),
			 new ItemStack(Items.FISH),
			 new ItemStack(ItemRegistry.greenheartfishItem),
			 new ItemStack(ItemRegistry.duckrawItem),
			 new ItemStack(ItemRegistry.musselrawItem),
			 new ItemStack(ItemRegistry.sardinerawItem),
			 new ItemStack(ItemRegistry.oysterrawItem)
										)
		);
	}
}
