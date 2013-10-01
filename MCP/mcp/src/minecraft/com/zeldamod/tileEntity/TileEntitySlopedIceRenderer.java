package com.zeldamod.tileEntity;

import org.lwjgl.opengl.GL11;

import com.zeldamod.misc.IceTriangle;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntitySlopedIceRenderer extends TileEntitySpecialRenderer {

	public TileEntitySlopedIceRenderer() {
		super();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		
		TileEntitySlopedIce tile = (TileEntitySlopedIce) tileentity;
		
		World world = tile.worldObj;
		
		GL11.glPushMatrix();
		GL11.glTranslated(d0, d1, d2);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();//(GL11.GL_TRIANGLES);
		tessellator.setColorRGBA_F(.7f, .7f, .9f, 1f);
		System.out.println(d0 + " " + d1 + " " + d2);
		for(IceTriangle triangle : tile.triangles)
		{
			tessellator.addVertex(triangle.x[0], triangle.y[0], triangle.z[0]);
			tessellator.addVertex(triangle.x[1], triangle.y[1], triangle.z[1]);
			tessellator.addVertex(triangle.x[2], triangle.y[2], triangle.z[2]);
			//System.out.println(triangle.x[0] + " " + triangle.y[0] + " " + triangle.z[0]);
		}
		tessellator.draw();
		
		GL11.glPopMatrix();
	}

}
