package com.zeldamod.block;

import java.util.ArrayList;
import java.util.Random;

import com.zeldamod.ZeldaMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSpecialTallGrass extends Block
{
	public BlockSpecialTallGrass(int id) 
	{
		super(id, Material.vine);
        this.setStepSound(soundGrassFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}

	public void updateTick(World par1World, int x, int y, int z, Random par5Random) 
	{
		if(par1World.getBlockId(x, y -1, z) != ZeldaMod.specialGrass.blockID)
		{
			par1World.setBlockToAir(x, y, z);
		}
	}

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
    }
	
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
    	ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (world.rand.nextInt(3) != 0)
        {
            return ret;
        }
        else
        {
        	ret.add(new ItemStack(ZeldaMod.rupee));
        	return ret;
        }
        
        
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }
}
