package com.variousadditions.event;

import java.util.Random;

import com.variousadditions.registries.VAItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class VAEventHandler {
	
	@SubscribeEvent
	public void onGlassCoinTossed(ItemTossEvent event) {
		
		Random rand = new Random();
		
		ItemEntity item = event.getEntityItem();
		
		World world = item.getEntityWorld();
		
		if (item.getItem().getItem() == VAItems.GLASS_COIN) {
			
			if (!world.isRemote) {
				
				world.playSound((PlayerEntity)null, new BlockPos(item.posX, item.posY, item.posZ), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, rand.nextFloat() * 0.2F + 0.4F);
				
			}
			
			item.remove();
			
		}
		
	}
	
	@SubscribeEvent
	public void dropRandomCoin(LivingDeathEvent event) {
		
		Random rand = new Random();
		
		Entity entity = event.getEntity();
		
		World world = entity.getEntityWorld();
		
		if (!world.isRemote && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
			
			float chance = rand.nextFloat() * (100 - 0) + 0;
			
			Item item = null;
			
			if (!(entity instanceof PlayerEntity)) {
				
				if (chance <= 0.001) {
					
					item = VAItems.MISSING_COIN;
					
				} else if (entity instanceof GuardianEntity || entity instanceof ElderGuardianEntity) {
					
					if (chance <= 0.5) {
						
						item = VAItems.PRISMARINE_COIN;
						
					}
					
				} else if (entity instanceof WitherEntity) {
					
					if (chance <= 0.175) {
						
						item = VAItems.NETHER_STAR_COIN;
						
					}
					
				} else if (entity instanceof EndermanEntity) {
					
					if (chance <= 0.5) {
						
						item = VAItems.ENDER_COIN;
						
					}
					
				} else if (entity instanceof VillagerEntity) {
					
					if (chance <= 0.2) {
						
						item = VAItems.BELL_COIN;
						
					}
					
				} else if (entity instanceof WitchEntity) {
					
					if (chance <= 0.25) {
						
						item = VAItems.THAUMIUM_COIN;
						
					}
					
				} else if (entity instanceof MonsterEntity) {
					
					if (chance <= 0.0875) {
						
						item = VAItems.NETHER_STAR_COIN;
						
					} else if (chance <= 0.1) {
						
						item = VAItems.BELL_COIN;
						
					} else if (chance <= 0.175) {
						
						item = VAItems.EMERALD_COIN;
						
					} else if (chance <= 0.25) {
						
						int tempChance = rand.nextInt((2 + 1) - 0) + 0;
						
						if (tempChance == 0) {
							
							item = VAItems.DIAMOND_COIN;
							
						} else if (tempChance == 1) {
							
							item = VAItems.PRISMARINE_COIN;
							
						} else if (tempChance == 2) {
							
							item = VAItems.ENDER_COIN;
							
						}
						
					} else if (chance <= 0.30) {
						
						item = VAItems.GLASS_COIN;
						
					} else if (chance <= 0.5) {
						
						if (rand.nextBoolean()) {
							
							item = VAItems.GOLD_COIN;
							
						} else {
							
							item = VAItems.BAMBOO_COIN;
							
						}
						
						item = VAItems.GOLD_COIN;
						
					} else if (chance <= 1) {
						
						if (rand.nextBoolean()) {
							
							item = VAItems.IRON_COIN;
							
						} else {
							
							item = VAItems.COOKIE_COIN;
							
						}
						
					} else if (chance <= 1.5) {
						
						item = VAItems.STONE_COIN;
						
					} else if (chance <= 2) {
						
						item = VAItems.WOODEN_COIN;
						
					}
					
				}
				
			}
			
			if (item != null) {
				
				ItemEntity itementity = new ItemEntity(world, entity.posX, entity.posY, entity.posZ, new ItemStack(item));
				itementity.setDefaultPickupDelay();
				world.addEntity(itementity);
				
			}
			
		}
		
	}
	
}