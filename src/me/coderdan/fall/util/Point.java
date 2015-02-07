package me.coderdan.fall.util;

public class Point
{

	private int x;
	private int y;
	
	public Point()
	{
		this(0, 0);
	}
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void translate(int x, int y)
	{
		this.x += x;
		this.y += y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
}
