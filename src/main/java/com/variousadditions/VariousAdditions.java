package com.variousadditions;

import net.minecraft.block.Block;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.variousadditions.event.VAEventHandler;
import com.variousadditions.itemgroup.VariousAdditionsItemGroup;
import com.variousadditions.registries.VABlocks;
import com.variousadditions.registries.VAItems;
import com.variousadditions.registries.VAPaintings;

@Mod("variousadditions")
public class VariousAdditions {
	
	public static final String modid = "variousadditions";
	
	public static final ItemGroup VARIOUSADDITIONS = new VariousAdditionsItemGroup("various_additions");
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();
	
	public VariousAdditions() {
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new VAEventHandler());
		
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		
	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		
		//ScreenManager.registerFactory(VAContainers.BLAST_SMOKER, (IScreenFactory<BlastSmokerContainer, BlastSmokerScreen>)BlastSmokerScreen::new);
		
	}
	
	private void enqueueIMC(final InterModEnqueueEvent event) {
		
	}
	
	private void processIMC(final InterModProcessEvent event) {
		
	}
	
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			
			VABlocks.registerBlocks(blockRegistryEvent);
			
		}
		
		@SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
			
			VAItems.registerItems(itemRegistryEvent);
			
		}
		
		@SubscribeEvent
		public static void onPaintingsRegistry(final RegistryEvent.Register<PaintingType> paintingRegistryEvent) {
			
			VAPaintings.registerPaintings(paintingRegistryEvent);
			
		}
		
		@SubscribeEvent
		public static void onTileEntitiesRegistry(final RegistryEvent.Register<TileEntityType<?>> tileEntityRegistryEvent) {
			
			//VATileEntities.registerTileEntites(tileEntityRegistryEvent);
			
		}
		
		@SubscribeEvent
		public static void onContainersRegistry(final RegistryEvent.Register<ContainerType<?>> containerRegistryEvent) {
			
			//VAContainers.registerContainers(containerRegistryEvent);
			
		}
		
	}
	
	public static String getModLocation(String name) {
		
		return modid + ":" + name;
		
	}
	
}
