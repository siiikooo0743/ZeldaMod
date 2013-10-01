package com.zeldamod.item;

import com.zeldamod.ZeldaMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemIceTriangleOrigin extends Item 
{
	
	public static int ox, oy, oz;
	public static boolean originSet = false;
	
	public ItemIceTriangleOrigin(int par1) 
	{
		super(par1);
		setCreativeTab(CreativeTabs.tabTools);
		setTextureName("zeldamod:ice_triangle_origin");
	}
	
	@Override
	public Icon getIcon(ItemStack stack, int pass) 
	{
		return itemIcon;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		
		if(originSet)
		{
			par2EntityPlayer.addChatMessage("Scan bounds from ("+ ox + ", " + oy + ", " + oz + ") to (" + par4 + ", " + (par5 - 1) + ", " + par6 + ")");
			par1ItemStack.itemID = ZeldaMod.slopedIce.itemID;
			par1ItemStack.setTagCompound(ItemSlopedIce.getNBTWithTriangles(ItemIceTriangleCreator.triangles));
			
			originSet = false;
		}
		else
		{
			par2EntityPlayer.addChatMessage("Set origin ("+ par4 + ", " + par5 + ", " + par6 + ")");
			ox = par4;
			oy = par5;
			oz = par6;
			originSet = true;
		}
		
		return true;
	}

}
