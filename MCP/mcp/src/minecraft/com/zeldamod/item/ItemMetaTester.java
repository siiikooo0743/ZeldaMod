package com.zeldamod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMetaTester extends Item
{

	public ItemMetaTester(int id) 
	{
		super(id);
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		par2EntityPlayer.addChatMessage("" + par2EntityPlayer.worldObj.getBlockMetadata(par4, par5, par6));
		return true;
    }
	
}
