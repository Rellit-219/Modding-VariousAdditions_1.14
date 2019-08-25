package com.variousadditions.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;

public class FuelItem extends Item implements IForgeItem {
	
	public int burnTime;
	
	public FuelItem(Properties properties, int burnTime) {
		super(properties);
		
		this.burnTime = burnTime;
		
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		
        return burnTime;
        
    }
	
}