package com.zeldamod.block;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockLockedDoor extends BlockDoor
{
	public BlockLockedDoor(int id, Material material) 
	{
		super(id, material);
		
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(getFullMetadata(par1World, par2, par3, par4) == 2)
		{
			if(par5EntityPlayer.inventory.getCurrentItem().getItem() instanceof Item)
			{
				par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, new ItemStack(0, 1, 0));
			}
			return false;
		}
		else
		{
			int i1 = this.getFullMetadata(par1World, par2, par3, par4);
            int j1 = i1 & 7;
            j1 ^= 4;

            if ((i1 & 8) == 0)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, j1, 2);
                par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
            }
            else
            {
                par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, j1, 2);
                par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2, par3, par4);
            }

            par1World.playAuxSFXAtEntity(par5EntityPlayer, 1003, par2, par3, par4, 0);
            return true;
		}
    }
	
	@Override
	public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean par5)
    {
        if (getFullMetadata(par1World, par2, par3, par4) == 2)
        {
        	return;
        }
        else
        {
            
            if (getFullMetadata(par1World, par2, par3, par4) == 0)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
                par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
            }
            else
            {
                par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, 0, 2);
                par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2, par3, par4);
            }

            par1World.playAuxSFXAtEntity((EntityPlayer)null, 1003, par2, par3, par4, 0);
        }
    }

	
}
