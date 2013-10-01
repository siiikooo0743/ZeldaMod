package com.zeldamod.misc;

public class IceTriangle
{
	
	public double[] x, y, z;
	public boolean is_top;

	public IceTriangle(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, boolean is_top) 
	{
		this.x = new double[] { x1, x2, x3 };
		this.y = new double[] { y1, y2, y3 };
		this.z = new double[] { z1, z2, z3 };
		this.is_top = is_top;
	}
	
	public void setVertex(int idx, double x, double y, double z)
	{
		this.x[idx] = x;
		this.y[idx] = y;
		this.z[idx] = z;
	}

}
