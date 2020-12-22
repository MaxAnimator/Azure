package com.maxanimator.azure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("azure")
public class Azure
{
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "azure";

    public Azure() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        
        Registries.init();

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setup(final FMLCommonSetupEvent event) { }

    private void doClientStuff(final FMLClientSetupEvent event) {
    	event.enqueueWork(new Runnable() {
			public void run() {
				ItemModelsProperties.registerProperty(Registries.iron_spear.get(), new ResourceLocation("azure:spear_throwing"), (itemstack, clientworld, livingentity) -> {
					return livingentity != null && livingentity.isHandActive() && livingentity.getActiveItemStack() == itemstack ? 1.0F : 0.0F;
				});
			}
		});
    }
    
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) { }
    
    public static final ItemGroup TAB = new ItemGroup("Azure") {
		
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(Registries.stone_wall.get());
		}
	};
}
