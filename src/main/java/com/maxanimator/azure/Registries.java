package com.maxanimator.azure;

import com.maxanimator.azure.items.ModdedSpearItem;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registries {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Azure.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Azure.MOD_ID);
	
	public static void init()
	{
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<Item> iron_spear = ITEMS.register("iron_spear", () -> new ModdedSpearItem(new Item.Properties().group(Azure.TAB).maxStackSize(1)));
	
	public static final RegistryObject<Block> stone_wall = BLOCKS.register("stone_wall", () -> new WallBlock(WallBlock.Properties.create(Material.ROCK)));
	public static final RegistryObject<Block> dirt_slab = BLOCKS.register("dirt_slab", () -> new SlabBlock(SlabBlock.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(0.5F)));
	
	public static final RegistryObject<Item> stone_wall_item = ITEMS.register("stone_wall", () -> new BlockItem(stone_wall.get(), new Item.Properties().group(Azure.TAB)));
	public static final RegistryObject<Item> dirt_slab_item = ITEMS.register("dirt_slab", () -> new BlockItem(dirt_slab.get(), new Item.Properties().group(Azure.TAB)));
}
