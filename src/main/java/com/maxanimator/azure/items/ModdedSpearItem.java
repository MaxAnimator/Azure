package com.maxanimator.azure.items;

import com.maxanimator.azure.Registries;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

public class ModdedSpearItem extends Item{
	
	public ModdedSpearItem(Properties properties)
	{
		super(properties);
	}
	
	public float spear_throwing = 0.0F;
	
	@Override
	public UseAction getUseAction(ItemStack stack)
	{
	    return UseAction.SPEAR;
	}
	
	@Override
	public int getUseDuration(ItemStack stack)
	{
	    return 32000;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemheld = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(ActionResultType.PASS, itemheld);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
	      if (entityLiving instanceof PlayerEntity) {
	         PlayerEntity playerentity = (PlayerEntity)entityLiving;
	         int i = this.getUseDuration(stack) - timeLeft;
	         if (i >= 10) {
	        	 TridentEntity tridententity = new TridentEntity(worldIn, playerentity, stack);
                 tridententity.func_234612_a_(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, 2.5F + 0.5F, 1.0F);
                 if (playerentity.abilities.isCreativeMode) {
                    tridententity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                 }
                 
	        	 worldIn.addEntity(tridententity);
                 worldIn.playMovingSound((PlayerEntity)null, tridententity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                 if (!playerentity.abilities.isCreativeMode) {
                    playerentity.inventory.deleteStack(stack);
                 }
	         }
	      }
	}
	
	/*
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      if (itemstack.getDamage() >= itemstack.getMaxDamage() - 1) {
	         return ActionResult.resultFail(itemstack);
	      } 
	      else {
	         playerIn.setActiveHand(handIn);
	         return ActionResult.resultConsume(itemstack);
	      }
	   }
*/
}
