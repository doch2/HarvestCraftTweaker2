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

public class GroundTrapRecipes {
	public static final Map<Ingredient, List<ItemStack>> groundTrapRecipes = new HashMap<>();

	public static final Random rand = new Random();

	public static void addTrapRecipe(Ingredient input, List<ItemStack> outputs) {
		groundTrapRecipes.put(input, outputs);
	}

	public static ItemStack getResult(ItemStack input) {
		return groundTrapRecipes.entrySet().stream()
						.filter(entry -> entry.getKey().test(input))
						.map(Map.Entry::getValue).findFirst()
						.map(stacks -> stacks.get(rand.nextInt(stacks.size()))).orElse(ItemStack.EMPTY);
	}

	public static boolean isValidInput(ItemStack input){
		return groundTrapRecipes.entrySet().stream().anyMatch(entry -> entry.getKey().test(input));
	}

	static {
		addTrapRecipe(Ingredient.fromStacks(new ItemStack(ItemRegistry.grainbaitItem)),
						Lists.newArrayList(
										new ItemStack(ItemRegistry.turkeyrawItem),
										new ItemStack(Items.BEEF),
										new ItemStack(Items.CHICKEN),
										new ItemStack(Items.LEATHER),
										new ItemStack(Items.FEATHER),
										new ItemStack(Items.EGG),
										new ItemStack(Items.BONE),
										new ItemStack(Items.CHICKEN),
										new ItemStack(ItemRegistry.duckrawItem),
										new ItemStack(ItemRegistry.turkeyrawItem),
										new ItemStack(Items.FEATHER))
										);

		addTrapRecipe(Ingredient.fromStacks(new ItemStack(ItemRegistry.fruitbaitItem)),
						Lists.newArrayList(
										new ItemStack(Items.RABBIT),
										new ItemStack(Items.LEATHER),
										new ItemStack(Items.FEATHER),
										new ItemStack(Items.EGG),
										new ItemStack(Items.BONE),
										new ItemStack(Items.RABBIT_FOOT),
										new ItemStack(Items.RABBIT_HIDE),
										new ItemStack(Items.CHICKEN),
										new ItemStack(Items.CHICKEN),
										new ItemStack(ItemRegistry.duckrawItem),
										new ItemStack(Items.FEATHER))
		);

		addTrapRecipe(Ingredient.fromStacks(new ItemStack(ItemRegistry.veggiebaitItem)),
						Lists.newArrayList(
										new ItemStack(ItemRegistry.venisonrawItem),
										new ItemStack(Items.MUTTON),
										new ItemStack(Items.PORKCHOP),
										new ItemStack(Items.LEATHER),
										new ItemStack(Items.FEATHER),
										new ItemStack(Items.EGG),
										new ItemStack(Items.BONE),
										new ItemStack(Items.CHICKEN),
										new ItemStack(Items.CHICKEN),
										new ItemStack(ItemRegistry.duckrawItem),
										new ItemStack(Items.FEATHER))
		);
	}
}
