package com.variousadditions.jei;

import com.variousadditions.VariousAdditions;
import com.variousadditions.registries.VAItems;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class VariousAdditionsJeiPlugin implements IModPlugin {
	
	@Override
	public ResourceLocation getPluginUid() {
		
		return new ResourceLocation(VariousAdditions.modid);
		
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		
		this.addCoinDescription(registration, VAItems.WOODEN_COIN, "wooden");
		this.addCoinDescription(registration, VAItems.STONE_COIN, "stone");
		this.addCoinDescription(registration, VAItems.IRON_COIN, "iron");
		this.addCoinDescription(registration, VAItems.GOLD_COIN, "gold");
		this.addCoinDescription(registration, VAItems.DIAMOND_COIN, "diamond");
		this.addCoinDescription(registration, VAItems.EMERALD_COIN, "emerald");
		this.addCoinDescription(registration, VAItems.PRISMARINE_COIN, "prismarine");
		this.addCoinDescription(registration, VAItems.GLASS_COIN, "glass");
		this.addCoinDescription(registration, VAItems.NETHER_STAR_COIN, "nether_star");
		this.addCoinDescription(registration, VAItems.BAMBOO_COIN, "bamboo");
		this.addCoinDescription(registration, VAItems.ENDER_COIN, "ender");
		this.addCoinDescription(registration, VAItems.COOKIE_COIN, "cookie");
		this.addCoinDescription(registration, VAItems.BELL_COIN, "bell");
		this.addCoinDescription(registration, VAItems.MISSING_COIN, "missing");
		this.addCoinDescription(registration, VAItems.THAUMIUM_COIN, "thaumium");
		
	}
	
	public void addCoinDescription(IRecipeRegistration registration, Item item, String coinName) {
		
		registration.addIngredientInfo(new ItemStack(item), VanillaTypes.ITEM, "jei.variousadditions.coin_description", "jei.variousadditions." + coinName + "_coin_description");
		
	}
	
}