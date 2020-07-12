package tfar.harvestcrafttweaker;

import com.pam.harvestcraft.tileentities.MarketData;
import com.pam.harvestcraft.tileentities.ShippingBinData;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ZenRegister
@ZenClass("mods.harvestcrafttweaker.HarvestCraftTweaker")
public class Methods {

	//note: these recipes will initially load when being accessed by these methods

	public static final List<ItemStack> marketOutputsToRemove = new ArrayList<>();
	public static final List<MarketData> marketToAdd = new ArrayList<>();

	public static boolean clearmarket = false;

	@ZenMethod
	public static void clearAllPressing(){
		ReflectionHacks.clearAllPressingRecipes();
	}

	@ZenMethod
	public static void clearAllMarket() {
		clearmarket = true;
	}

	@ZenMethod
	public static void addPressing(IIngredient input, IItemStack leftOutput, IItemStack rightOutput) {
		for (IItemStack inputStack : input.getItems())
		ReflectionHacks.makePressingRecipe(CraftTweakerMC.getItemStack(inputStack), CraftTweakerMC.getItemStack(leftOutput),
						CraftTweakerMC.getItemStack(rightOutput));
	}

	@ZenMethod
	public static void removePressingByInput(IItemStack input) {
		ReflectionHacks.removePressingRecipeByInput(CraftTweakerMC.getItemStack(input));
	}

	//note: these recipes will initially load when being accessed by these methods

	@ZenMethod
	public static void clearAllGrinding(){
		ReflectionHacks.clearAllGrindingRecipes();
	}

	@ZenMethod
	public static void addGrinding(IIngredient input, IItemStack leftOutput, IItemStack rightOutput) {
		for (IItemStack inputStack : input.getItems())
			ReflectionHacks.makeGrindingRecipe(CraftTweakerMC.getItemStack(inputStack), CraftTweakerMC.getItemStack(leftOutput),
							CraftTweakerMC.getItemStack(rightOutput));
	}

	@ZenMethod
	public static void removeGrindingByInput(IItemStack input) {
		ReflectionHacks.removeGrindingRecipeByInput(CraftTweakerMC.getItemStack(input));
	}

	//==========================================

	@ZenMethod
	public static void addWaterFilter(IIngredient input, IItemStack leftOutput, IItemStack rightOutput) {
		ReflectionHacks.makeGrindingRecipe(CraftTweakerMC.getItemStack(input), CraftTweakerMC.getItemStack(leftOutput),
						CraftTweakerMC.getItemStack(rightOutput));
	}

	@ZenMethod
	public static void removeWaterFilterByInput(IItemStack input) {
		ReflectionHacks.removeGrindingRecipeByInput(CraftTweakerMC.getItemStack(input));
	}

	//ground traps, stupidly hardcoded so needed coremods

	@ZenMethod
	public static void addGroundTrap(IIngredient input, IItemStack[] outputs) {
		GroundTrapRecipes.addTrapRecipe(CraftTweakerMC.getIngredient(input),
						Arrays.stream(outputs).map(CraftTweakerMC::getItemStack).collect(Collectors.toList()));
	}

	@ZenMethod
	public static void removeGroundTrapByInput(IIngredient input) {
		GroundTrapRecipes.groundTrapRecipes.remove(CraftTweakerMC.getIngredient(input));
	}

	//water traps, stupidly hardcoded so needed coremods as well

	@ZenMethod
	public static void addWaterTrap(IIngredient input, IItemStack[] outputs) {
		WaterTrapRecipes.addTrapRecipe(CraftTweakerMC.getIngredient(input),
						Arrays.stream(outputs).map(CraftTweakerMC::getItemStack).collect(Collectors.toList()));
	}

	@ZenMethod
	public static void removeWaterTrapByInput(IIngredient input) {
		WaterTrapRecipes.waterTrapRecipes.remove(CraftTweakerMC.getIngredient(input));
	}

	//market

	@ZenMethod
	public static void addMarket(IItemStack toBuy,IItemStack input,int cost) {
		MarketData marketData = new MarketData(CraftTweakerMC.getItemStack(toBuy),CraftTweakerMC.getItemStack(input),cost);
		Methods.marketToAdd.add(marketData);
	}

	@ZenMethod
	public static void removeMarketByOutput(IItemStack output) {
		Methods.marketOutputsToRemove.add(CraftTweakerMC.getItemStack(output));
	}

	//shipping bin

	@ZenMethod
	public static void addShipping(IItemStack toBuy,IItemStack input,int cost) {
		ShippingBinData shippingBinData = new ShippingBinData(CraftTweakerMC.getItemStack(toBuy),CraftTweakerMC.getItemStack(input),cost);
		ReflectionHacks.addShippingTrade(shippingBinData);
	}

	@ZenMethod
	public static void removeShippingByOutput(IItemStack output) {
		ReflectionHacks.removeShippingTrade(CraftTweakerMC.getItemStack(output));
	}
}
