package com.zeldamod.item;

import com.zeldamod.ZeldaMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemLockedDoor extends Item
{

	public ItemLockedDoor(int id) 
	{
		super(id);
		
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		 if (par7 != 1)
	        {
	            return false;
	        }
	        else
	        {
	            ++par5;
	            Block block;
	            block = ZeldaMod.lockedDoor;

	            if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack))
	            {
	                if (!block.canPlaceBlockAt(par3World, par4, par5, par6))
	                {
	                    return false;
	                }
	                else
	                {
	                    int i1 = MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
	                    placeDoorBlock(par3World, par4, par5, par6, i1, block);
	                    --par1ItemStack.stackSize;
	                    return true;
	                }
	            }
	            else
	            {
	                return false;
	            }
	        }
    }
	
	public static void placeDoorBlock(World par0World, int par1, int par2, int par3, int par4, Block par5Block)
    {
        par0World.setBlock(par1, par2, par3, par5Block.blockID, par4, 2);
        par0World.setBlock(par1, par2 + 1, par3, par5Block.blockID, par4, 2);
        par0World.notifyBlocksOfNeighborChange(par1, par2, par3, par5Block.blockID);
        par0World.notifyBlocksOfNeighborChange(par1, par2 + 1, par3, par5Block.blockID);
    }
	
}
