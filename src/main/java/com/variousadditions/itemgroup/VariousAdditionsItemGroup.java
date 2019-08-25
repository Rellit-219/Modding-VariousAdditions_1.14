package com.variousadditions.itemgroup;

import com.variousadditions.registries.VAItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VariousAdditionsItemGroup extends ItemGroup {

	public VariousAdditionsItemGroup(String label) {
		super(label);
		
	}

	@Override
	public ItemStack createIcon() {
		
		return new ItemStack(VAItems.PRISMARINE_COIN);
		
	}
	
}