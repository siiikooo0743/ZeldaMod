package com.zeldamod.slopedice;

import java.util.ArrayList;
import java.util.List;

import com.zeldamod.ZeldaMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSlopedIce extends Item {

	public ItemSlopedIce(int par1) {
		super(par1);
	}
	
	public static NBTTagCompound getNBTWithTriangles(List<IceTriangle> triangles)
	{		
		NBTTagCompound nbt = new NBTTagCompound();
		for (int i = 0; i < triangles.size(); ++i) {
			IceTriangle iceTriangle = triangles.get(i);
			NBTTagCompound nbt_triangle = new NBTTagCompound();
			nbt_triangle.setDouble("x0", iceTriangle.x[0]);
			nbt_triangle.setDouble("y0", iceTriangle.x[0]);
			nbt_triangle.setDouble("z0", iceTriangle.x[0]);
			
			nbt_triangle.setDouble("x1", iceTriangle.x[1]);
			nbt_triangle.setDouble("y1", iceTriangle.x[1]);
			nbt_triangle.setDouble("z1", iceTriangle.x[1]);

			nbt_triangle.setDouble("x2", iceTriangle.x[2]);
			nbt_triangle.setDouble("y2", iceTriangle.x[2]);
			nbt_triangle.setDouble("z2", iceTriangle.x[2]);
			nbt_triangle.setBoolean("is_top", iceTriangle.is_top);
			nbt.setCompoundTag("triangle_" + i, nbt_triangle);
		}
		nbt.setInteger("triangle_count", triangles.size());
		
		return nbt;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		
		NBTTagCompound nbt = stack.getTagCompound();
		int n = nbt.getInteger("triangle_count");
		List<IceTriangle> triangles = new ArrayList<IceTriangle>(n);
		
		for(int i = 0; i < n; ++i)
		{
			NBTTagCompound triangle = nbt.getCompoundTag("triangle_" + i);
			
			triangles.add(new IceTriangle(triangle.getDouble("x0"), 
					                      triangle.getDouble("y0"), 
					                      triangle.getDouble("z0"), 
					                      triangle.getDouble("x1"), 
					                      triangle.getDouble("y1"), 
					                      triangle.getDouble("z1"), 
					                      triangle.getDouble("x2"), 
					                      triangle.getDouble("y2"), 
					                      triangle.getDouble("z2"), 
					                      triangle.getBoolean("is_top")));
		}
		
		world.setBlock(x, y, z, ZeldaMod.slopedIceBlock.blockID);
		((TileEntitySlopedIce)world.getBlockTileEntity(x, y, z)).triangles = triangles;
		
		return true;
	}
	

}
