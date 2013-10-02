package com.zeldamod.block;
import java.util.Random;

import com.zeldamod.ZeldaMod;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLockedDoor extends Block
{
	public BlockLockedDoor(int id, Material material) 
	{
		super(id, material);
		float f = 0.5F;
        float f1 = 1.0F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
	}
	private void setMeta(World par1World, int x, int y, int z, int metadata)
	{
		par1World.setBlockMetadataWithNotify(x, y, z, metadata, 2);
		
		if(base(par1World, x, y, z))
		{
			par1World.setBlockMetadataWithNotify(x, y + 1, z, metadata, 2);
			par1World.markBlockRangeForRenderUpdate(x, y, z, x, y + 1, z);
		}
		else
		{
			par1World.setBlockMetadataWithNotify(x, y - 1, z, metadata, 2);
			par1World.markBlockRangeForRenderUpdate(x, y, z, x, y - 1, z);
		}
		
	}
	
	private boolean base(World par1World, int x, int y, int z)
	{
		return(par1World.getBlockId(x, y + 1, z) == this.blockID);
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		if(meta < 4)
		{
			ItemStack is = par5EntityPlayer.inventory.getCurrentItem();
			if(is == null)
			{
				
			}
			else if(is.getItem() == (ZeldaMod.key))
			{
				par5EntityPlayer.inventory.decrStackSize(par5EntityPlayer.inventory.currentItem, 1);
				setMeta(par1World, par2, par3, par4, meta + 4);
				par1World.playAuxSFXAtEntity((EntityPlayer)null, 1003, par2, par3, par4, 0);
			}
			
			return false;
		}
		else
		{
			if(meta < 8 && meta > 3)
			{
				setMeta(par1World, par2, par3, par4, meta + 4);
            }
            else
            {
            	setMeta(par1World, par2, par3, par4, meta - 4);
            }

            par1World.playAuxSFXAtEntity(par5EntityPlayer, 1003, par2, par3, par4, 0);
            return true;
		}
    }
	
	public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean newState)
    {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
        if (meta < 4)
        {
        	return;
        }
        else
        {
        	boolean state = (meta > 3 && meta < 8);
            if(state ^ newState)
            {
            	if(meta < 8 && meta > 3)
    			{
    				setMeta(par1World, par2, par3, par4, meta + 4);
                }
                else
                {
                	setMeta(par1World, par2, par3, par4, meta - 4);
                }
	
	            par1World.playAuxSFXAtEntity((EntityPlayer)null, 1003, par2, par3, par4, 0);
            }
        }
    }

	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		
		if(base(par1World, par2, par3, par4))
        {
            boolean flag = false;
            
            
            if (par1World.getBlockId(par2, par3 + 1, par4) != this.blockID)
            {
                par1World.setBlockToAir(par2, par3, par4);
                flag = true;
            }

            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4))
            {
                par1World.setBlockToAir(par2, par3, par4);
                flag = true;

                if (par1World.getBlockId(par2, par3 + 1, par4) == this.blockID)
                {
                    par1World.setBlockToAir(par2, par3 + 1, par4);
                }
            }

            if (flag)
            {
                if (!par1World.isRemote)
                {
                    this.dropBlockAsItem(par1World, par2, par3, par4, meta, 0);
                }
            }
            else
            {
                boolean flag1 = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);

                if ((flag1 || par5 > 0 && Block.blocksList[par5].canProvidePower()) && par5 != this.blockID)
                {
                    this.onPoweredBlockChange(par1World, par2, par3, par4, flag1);
                }
            }
        }
		else
		{
			
            if (par1World.getBlockId(par2, par3 - 1, par4) != this.blockID)
            {
                par1World.setBlockToAir(par2, par3, par4);
            }
            else
            {
                boolean flag1 = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);

                if ((flag1 || par5 > 0 && Block.blocksList[par5].canProvidePower()) && par5 != this.blockID)
                {
                    this.onPoweredBlockChange(par1World, par2, par3, par4, flag1);
                }
            }
		}
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public int getRenderType()
	{
		return 7;
	}
	 
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		
	    this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
	    return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	 
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
	    return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	 
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		this.setDoorRotation(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public int getDoorOrientation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
	    return par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 3;
	}

	public boolean isDoorOpen(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
	    return (par1IBlockAccess.getBlockMetadata(par2, par3, par4) > 4 && par1IBlockAccess.getBlockMetadata(par2, par3, par4) < 9);
	} 

	public int idDropped(int par1, Random par2Random, int par3)
    {
		return 0;
    }
	
    private void setDoorRotation(int par1)
    {
    	
        float f = 0.1875F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        int j = par1 & 3;
        boolean flag = (par1 & 4) != 0;
        boolean flag1 = (par1 & 16) != 0;

        if (j == 0)
        {
            if (flag)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
        }
        else if (j == 1)
        {
            if (flag)
            {
                if (!flag1)
                {
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            }
        }
        else if (j == 2)
        {
            if (flag)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                }
            }
            else
            {
                this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        }
        else if (j == 3)
        {
            if (flag)
            {
                if (!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            }
        }
    }

}
