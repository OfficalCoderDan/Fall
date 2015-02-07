package me.coderdan.fall.game;

import me.coderdan.fall.Graphics;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class Player
{

	public static final int X = 700 / 2 - 25;
	public static final int Y = 20;
	
	public Color colour;
	public double score;
	private me.coderdan.fall.util.Point point;
	private Rectangle rect;
	public boolean gameOver = false;
	private int gameOverTicks = 0;
	
	private TrueTypeFont FRANKLIN_GOTHIC_40;
	
	public Player()
	{
		colour = Color.green;
		score = 0.0;
		point = new me.coderdan.fall.util.Point(X, Y);
		rect = new Rectangle(point.getX(), point.getY(), 50, 50);
		
		FRANKLIN_GOTHIC_40 = new TrueTypeFont(new Font("Franklin Gothic", Font.PLAIN, 40), true);
	}
	
	public void down()
	{
		if(gameOver)
			return;
		point.translate(0, 1);
		rect.translate(0, 1);
	}
	
	public void left()
	{
		if(gameOver)
			return;
		point.translate(-2, 0);
		rect.translate(-2, 0);
	}
	
	public void right()
	{
		if(gameOver)
			return;
		point.translate(2, 0);
		rect.translate(2, 0);
	}
	
	public me.coderdan.fall.util.Point getPoint()
	{
		return point;
	}
	
	public Rectangle getRect()
	{
		return rect;
	}
	
	public void gameOver()
	{
		if(gameOver)
			return;
		gameOver = true;
	}
	
	public void respawn()
	{
		
	}
	
	public void draw()
	{
		Graphics.drawRect(colour, 50, 50, point.getX(), Y);
		
		if(gameOver)
		{
			if(gameOverTicks > 60)
			{
				gameOver = false;
				gameOverTicks = 0;
				score = 0.0;
				colour = Color.green;
				point.set(X, Y);
				rect.setLocation(X, Y);
			}
			else
			{
				gameOverTicks++;
				Graphics.drawText("Game Over!", FRANKLIN_GOTHIC_40, Color.magenta, 100, 50);
			}
		}
	}
	
	private void drawRepsawnDialog()
	{
		
	}
	
}
