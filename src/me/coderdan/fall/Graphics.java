package me.coderdan.fall;

import me.coderdan.fall.util.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import static org.lwjgl.opengl.GL11.*;

public class Graphics
{
	
	public static void drawText(String text, TrueTypeFont font, Color colour, float x, float y)
	{
		font.drawString(x, y, text, colour);
	}
	
	public static void drawRect(Color colour, double width, double height, float x, float y)
	{
		initColour(colour);
		glBegin(GL_QUADS);
			glVertex2d(x, y);
			glVertex2d(x + width, y);
			glVertex2d(x + width, y + height);
			glVertex2d(x, y + height);
		glEnd();
	}
	
	public static void drawOval(Color colour, double width, double height, float x, float y)
	{
		initColour(colour);
		
		float x2, y2, angle;
		
		glBegin(GL_TRIANGLE_FAN);
			glVertex2f(x, y);
			for(angle = 1.0f; angle < 361; angle += 0.2)
			{
				x2 = (float) (x + Math.sin(angle) * width);
				y2 = (float) (y + Math.cos(angle) * height);
				glVertex2f(x2, y2);
			}
		glEnd();
	}
	
	public static void drawLine(Color colour, Point p1, Point p2)
	{
		initColour(colour);
		glBegin(GL_LINES);
			glVertex2f(p1.getX(), p1.getY());
			glVertex2f(p2.getX(), p2.getX());
		glEnd();
	}
	
	public static void drawTriangle(Color colour, Point p1, Point p2, Point p3)
	{
		initColour(colour);
		glBegin(GL_TRIANGLES);
			glVertex2f(p1.getX(), p1.getY());
			glVertex2f(p2.getX(), p2.getY());
			glVertex2f(p3.getX(), p3.getY());
		glEnd();
	}
	
	public static void init2d()
	{
		glDisable(GL_TEXTURE_2D);
	}
	
	public static void initTexture2d()
	{
		glEnable(GL_TEXTURE_2D);
	}
	
	private static void initColour(Color colour)
	{
		glColor3d(colour.getRed() / 255, colour.getGreen() / 255, colour.getBlue() / 255);
	}

	
}
