package me.coderdan.fall.render;

import me.coderdan.fall.util.Graphics;
import me.coderdan.fall.util.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class Button
{

    private Color colour;
    private String text;
    private int width, height;
    private Point point;

    private TrueTypeFont ARIAL_20;

    public Button(Color colour, String text, int width, int height, Point point)
    {
        this.colour = colour;
        this.text = text;
        this.width = width;
        this.height = height;
        this.point = point;

        ARIAL_20 = new TrueTypeFont(new Font("Arial", Font.PLAIN, 20), true);
    }

    public Button(Color colour, String text, int width, int height, int x, int y)
    {
        this(colour, text, width, height, new Point(x, y));
    }

    public Button(Color colour, String text, int width, int height)
    {
        this(colour, text, width, height, 0, 0);
    }

    public Button(Color colour)
    {
        this(colour, "", 50, 25);
    }

    public Button(String text)
    {
        this(Color.white, text, 50, 25, 0, 0);
    }

    public Button()
    {
        this(Color.white);
    }

    public Color getColour()
    {
        return colour;
    }

    public void setColour(Color colour)
    {
        this.colour = colour;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Point getPoint()
    {
        return point;
    }

    public void setPoint(Point point)
    {
        this.point = point;
    }

    public void draw(Color textColour)
    {
        Graphics.init2d();
        Graphics.drawRect(colour, width, height, point.getX(), point.getY());

        Graphics.initTexture2d();
	    float middleX = ((point.getX() + width) / 2.25F) - (ARIAL_20.getWidth(text) / 4);
	    float middleY = ((point.getY() + height) / 2.25F) + (ARIAL_20.getHeight(text) / 4);
        Graphics.drawText(text, ARIAL_20, textColour, middleX, middleY);
    }

    public void update() { }

}
