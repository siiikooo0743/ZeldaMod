package com.zeldamod.tileEntity;

import com.zeldamod.ZeldaMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityPushable extends TileEntity {

	public TileEntityPushable() {
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		for(Object e : worldObj.playerEntities)
		{
			EntityPlayer player = (EntityPlayer)e;
			
			double px = player.posX;
			double py = player.posY - player.yOffset;
			double pz = player.posZ;
			
			double dy = Math.abs(py - yCoord);
			
			if(dy < 0.05)
			{
				boolean updated = false;
				int newX = 0;
				int newZ = 0;
				if(px > xCoord + 0.15 && px < xCoord + 0.85 &&
				    pz > zCoord - 0.31 && pz < zCoord &&
				    true)//player.motionZ > 0.005 ) // move up
				{
					newX = xCoord;
					newZ = zCoord + 1;
					updated  = true;
				}
				else if(pz > zCoord + 0.15 && pz < zCoord + 0.85 &&
				    px > xCoord - 0.31 && px < xCoord &&
				    true)//player.motionX > 0.005) // move right
				{
					newX = xCoord + 1;
					newZ = zCoord;
					updated  = true;
				}
				else if(px > xCoord + 0.15 && px < xCoord + 0.85 &&
						pz > zCoord + 1 && pz < zCoord + 1.31 &&
						true)//player.motionX < -0.005) // move down
				{
					newX = xCoord;
					newZ = zCoord - 1;
					updated  = true;
				}
				else if(pz > zCoord + 0.15 && pz < zCoord + 0.85 &&
						px > xCoord + 1 && px < xCoord + 1.31 &&
						true)//player.motionZ < -0.005) // move left
				{
					newX = xCoord - 1;
					newZ = zCoord;
					updated  = true;
				}
				if(updated)
				{
					if(worldObj.getBlockId(newX, yCoord, newZ) == 0)
					{
						worldObj.setBlock(newX, yCoord, newZ, ZeldaMod.pushable.blockID);
						worldObj.setBlock(xCoord, yCoord, zCoord, 0);
						worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
						worldObj.notifyBlockChange(xCoord, yCoord, zCoord, 0);
					}
				}
			}
		}
	}

}
