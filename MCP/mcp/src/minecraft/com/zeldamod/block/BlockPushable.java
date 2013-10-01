package com.zeldamod.block;

import com.zeldamod.tileEntity.TileEntityPushable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockPushable extends BlockContainer {

	public BlockPushable(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO Auto-generated constructor stub
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		// TODO Auto-generated method stub
		return Block.slowSand.getIcon(par1, par2);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new TileEntityPushable();
	}

}
