package com.zeldamod.block;

import java.util.Random;

import com.zeldamod.ZeldaMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockSpecialGrass extends Block
{

	public BlockSpecialGrass(int id) 
	{
		super(id, Material.grass);
        this.setStepSound(soundGrassFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setTickRandomly(true);
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
	    {
	        int l = par1World.getBlockMetadata(par2, par3, par4);
	        if (l < 7)
	        {
	            float f = 2.0F;
	            if (par5Random.nextInt((int)(25.0F / f) + 1) == 0)
	            {
	                par1World.setBlock(par2, par3, par4, ZeldaMod.specialTallGrass.blockID);
	            }
	        }
	    }
	 }
	

}
