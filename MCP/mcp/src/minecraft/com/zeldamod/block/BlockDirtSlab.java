package com.zeldamod.block;

import com.zeldamod.ZeldaMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDirtSlab extends BlockHalfSlab 
{
	@SideOnly(Side.CLIENT)
	public Icon grassSide;
	@SideOnly(Side.CLIENT)
	public Icon grassTop;
	
	public BlockDirtSlab(int par1, boolean par2, Material par3Material) 
	{
		super(par1, par2, par3Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
        setLightOpacity(0);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		if(blockID == ZeldaMod.dirtSlab.blockID)
			return Block.dirt.getBlockTextureFromSide(0);
		return side == 1 ? this.grassTop : (side == 0 ? Block.dirt.getBlockTextureFromSide(0) : this.grassSide);
	}
	
	//@SideOnly(Side.CLIENT)
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    /*public Icon getBlockTexture(IBlockAccess iBlockAccess, int x, int y, int z, int side)
    {
		//if(iBlockAccess.getBlockId(x, y, z) == ZeldaMod.dirtSlab.blockID)
		//	return Block.dirt.getBlockTextureFromSide(0);
        if (side == 1)
        {
            return this.grassTop;
        }
        else if (side == 0)
        {
            return Block.dirt.getBlockTextureFromSide(0);
        }
        else
        {
            return this.grassSide;
        }
    }*/
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public String getFullSlabName(int i) 
	{
		return "Dirt Slab";
	}
    
    /*@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, java.util.List par3List)
    {
    	par3List.add(new ItemStack(blockID, 1, 0));
    	par3List.add(new ItemStack(blockID, 1, 1));
    }*/
    
    @SideOnly(Side.CLIENT)
    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	if(par1IBlockAccess.getBlockId(par2, par3, par4) == ZeldaMod.dirtSlab.blockID)
    		return 0xFFFFFF;
    	//return 0xFFFFFF;
        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1)
        {
            for (int l1 = -1; l1 <= 1; ++l1)
            {
                int i2 = par1IBlockAccess.getBiomeGenForCoords(par2 + l1, par4 + k1).getBiomeGrassColor();
                l += (i2 & 16711680) >> 16;
                i1 += (i2 & 65280) >> 8;
                j1 += i2 & 255;
            }
        }

        return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) 
	{
		grassSide = par1IconRegister.registerIcon("zeldamod:grass_slab_side");
		grassTop = par1IconRegister.registerIcon("grass_top");
		
		
	}

}
