package com.zeldamod.slopedice;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemIceTriangleCreator extends Item {

	public static int vertexIdx = 0;
	public static List<IceTriangle> triangles = new ArrayList<IceTriangle>(3);
	
	public final boolean useAsTop;
	
	public ItemIceTriangleCreator(int par1, boolean useAsTop) {
		super(par1);
		setCreativeTab(CreativeTabs.tabTools);
		this.useAsTop = useAsTop;
	}
	
	
	@Override
	public boolean onItemUseFirst(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		
		par2EntityPlayer.addChatMessage("Add vertex " + (vertexIdx + 1));
		
		int rx, ry, rz;
		rx = par4 - ItemIceTriangleOrigin.ox;
		ry = par5 - ItemIceTriangleOrigin.oy;
		rz = par6 - ItemIceTriangleOrigin.oz;
		
		if(vertexIdx == 0)
		{
			par2EntityPlayer.addChatMessage("new Triangle");
			triangles.add(new IceTriangle(rx, ry, rz, 0, 0, 0, 0, 0, 0, useAsTop));
			vertexIdx++;
		}
		else
		{
			IceTriangle triangle = triangles.get(triangles.size() - 1);
			triangle.setVertex(vertexIdx, rx, ry, rz);
			vertexIdx = (vertexIdx+1) % 3;
			
			if(vertexIdx == 0)
			{
				par2EntityPlayer.addChatMessage("finished Triangle:");
				par2EntityPlayer.addChatMessage("vertex 1 (" + triangle.x[0] + ", " + triangle.y[0] + ", " + triangle.z[0] + ")");
				par2EntityPlayer.addChatMessage("vertex 2 (" + triangle.x[1] + ", " + triangle.y[1] + ", " + triangle.z[1] + ")");
				par2EntityPlayer.addChatMessage("vertex 3 (" + triangle.x[2] + ", " + triangle.y[2] + ", " + triangle.z[2] + ")");
			}
		}
		
		return true;
	}

}
