package com.zeldamod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockDeepSnow extends Block
{
	public BlockDeepSnow(int id)
	{
		super(id, Material.snow);
        this.setBlockBounds(0.0F, 0.0F, 0.0F , 1.0F, 1.0F, 1.0F);
        this.setStepSound(soundSnowFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.motionX *= 0.4D;
        par5Entity.motionZ *= 0.4D;
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	
	public boolean isOpaqueCube() 
	{
		return false;
	}

}
