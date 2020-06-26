package tfar.harvestcrafttweaker;

import com.pam.harvestcraft.item.GrinderRecipes;
import com.pam.harvestcraft.item.PresserRecipes;
import com.pam.harvestcraft.item.WaterFilterRecipes;
import com.pam.harvestcraft.tileentities.MarketData;
import com.pam.harvestcraft.tileentities.MarketItems;
import com.pam.harvestcraft.tileentities.ShippingBinData;
import com.pam.harvestcraft.tileentities.ShippingBinItems;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReflectionHacks {

	//PresserRecipes
	public static final Method registerPressingRecipe;
	public static final Field pressingList;

	//GrinderRecipes
	public static final Method registerGrindingRecipe;
	public static final Field grindingList;

	//WaterFilterRecipes
	public static final Method registerWaterFilterRecipe;
	public static final Field waterFilterList;

	//market
	public static final Field marketList;

	//shipping bin
	public static final Field shippingList;
	static {
		try {
			registerPressingRecipe = PresserRecipes.class.getDeclaredMethod("makeItemStackRecipe", ItemStack.class, ItemStack.class, ItemStack.class);
			registerPressingRecipe.setAccessible(true);

			pressingList = PresserRecipes.class.getDeclaredField("pressingList");
			pressingList.setAccessible(true);

			registerGrindingRecipe = GrinderRecipes.class.getDeclaredMethod("makeItemStackRecipe", ItemStack.class, ItemStack.class, ItemStack.class);
			registerGrindingRecipe.setAccessible(true);

			grindingList = GrinderRecipes.class.getDeclaredField("grindingList");
			grindingList.setAccessible(true);

			registerWaterFilterRecipe = WaterFilterRecipes.class.getDeclaredMethod("makeItemStackRecipe", ItemStack.class, ItemStack.class, ItemStack.class);
			registerWaterFilterRecipe.setAccessible(true);

			waterFilterList = WaterFilterRecipes.class.getDeclaredField("waterfilterList");
			waterFilterList.setAccessible(true);

			marketList = MarketItems.class.getDeclaredField("items");
			marketList.setAccessible(true);

			shippingList = ShippingBinItems.class.getDeclaredField("items");
			shippingList.setAccessible(true);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void makePressingRecipe(ItemStack input, ItemStack leftOutput,ItemStack rightOutput) {
		try {
			registerPressingRecipe.invoke(null,input,leftOutput,rightOutput);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}


	public static void removePressingRecipeByInput(ItemStack input) {
		try {
			Map<ItemStack,ItemStack[]> map = (Map<ItemStack, ItemStack[]>) pressingList.get(null);
			for (Iterator<Map.Entry<ItemStack, ItemStack[]>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
				Map.Entry<ItemStack, ItemStack[]> entry = iterator.next();
				ItemStack stack = entry.getKey();
				if (ItemStack.areItemsEqual(input, stack))
					iterator.remove();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void clearAllPressingRecipes() {
		try {
			Map<ItemStack,ItemStack[]> map = (Map<ItemStack, ItemStack[]>) pressingList.get(null);
			map.clear();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//====================================================================================================

	public static void makeGrindingRecipe(ItemStack input, ItemStack leftOutput, ItemStack rightOutput) {
		try {
			registerGrindingRecipe.invoke(null,input,leftOutput,rightOutput);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public static void removeGrindingRecipeByInput(ItemStack input) {
		try {
			Map<ItemStack,ItemStack[]> map = (Map<ItemStack, ItemStack[]>) grindingList.get(null);
			Iterator<Map.Entry<ItemStack, ItemStack[]>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<ItemStack, ItemStack[]> entry = iterator.next();
				ItemStack stack = entry.getKey();
				if (ItemStack.areItemsEqual(input, stack))
					iterator.remove();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void clearAllGrindingRecipes() {
		try {
			Map<ItemStack,ItemStack[]> map = (Map<ItemStack, ItemStack[]>) grindingList.get(null);
			map.clear();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//=========================================================================================


	public static void makeWaterFilterRecipe(ItemStack input, ItemStack leftOutput, ItemStack rightOutput) {
		try {
			registerWaterFilterRecipe.invoke(null,input,leftOutput,rightOutput);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public static void removeWaterFilterByInput(ItemStack input) {
		try {
			Map<ItemStack,ItemStack[]> map = (Map<ItemStack, ItemStack[]>) waterFilterList.get(null);
			Iterator<Map.Entry<ItemStack, ItemStack[]>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<ItemStack, ItemStack[]> entry = iterator.next();
				ItemStack stack = entry.getKey();
				if (ItemStack.areItemsEqual(input, stack))
					iterator.remove();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	//=========================================================================================

	public static void addMarketTrade(MarketData marketData){
		try {
			List<MarketData> marketDataList = (List<MarketData>) marketList.get(null);
			marketDataList.add(marketData);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void removeMarketTrade(ItemStack stack){
		try {
			List<MarketData> marketData = (List<MarketData>) marketList.get(null);
			marketData.removeIf(data -> ItemStack.areItemsEqual(data.getItem(), stack));
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}


	public static void addShippingTrade(ShippingBinData data){
		try {
			List<ShippingBinData> shippingBinDatas = (List<ShippingBinData>) shippingList.get(null);
			shippingBinDatas.add(data);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void removeShippingTrade(ItemStack stack){
		try {
			List<ShippingBinData> shippingBinDatas = (List<ShippingBinData>) shippingList.get(null);
			shippingBinDatas.removeIf(data -> ItemStack.areItemsEqual(data.getItem(), stack));
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}



}
