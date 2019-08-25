package com.variousadditions.item;

import java.util.List;

import javax.annotation.Nullable;

import com.variousadditions.registries.VAItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeItem;

public class CoinItem extends Item implements IForgeItem {
	
	protected String description;
	
	public CoinItem(Properties properties, String description) {
		super(properties);
		
		this.description = description;
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		long ringTime = 0;
		List<LivingEntity> entitiesAtRing = null;
		
		if (itemstack.getItem() == VAItems.BELL_COIN) {
			
			if (!worldIn.isRemote) {
				
				worldIn.playSound((PlayerEntity)null, playerIn.getPosition(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 2.0F, 1.0F);
				
			}
			
			BlockPos blockpos = playerIn.getPosition();
			
			if (worldIn.getGameTime() > ringTime + 60L || entitiesAtRing == null) {
				
				ringTime = worldIn.getGameTime();
				AxisAlignedBB axisalignedbb = (new AxisAlignedBB(blockpos)).grow(48.0D);
				entitiesAtRing = worldIn.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
				
			}
			if (!worldIn.isRemote) {
				
				for(LivingEntity livingentity : entitiesAtRing) {
					
					if (livingentity.isAlive() && !livingentity.removed && blockpos.withinDistance(livingentity.getPositionVec(), 32.0D)) {
						
						livingentity.getBrain().setMemory(MemoryModuleType.HEARD_BELL_TIME, worldIn.getGameTime());
						
					}
					
				}
				
			}
			
			if (playerIn != null) {
				
				playerIn.addStat(Stats.BELL_RING);
				
			}
			
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
			
		}
		
		return new ActionResult<>(ActionResultType.PASS, itemstack);
		
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		
		tooltip.add((new TranslationTextComponent(this.description)).applyTextStyle(TextFormatting.GRAY));
		
	}
	
}