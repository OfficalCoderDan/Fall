package me.coderdan.fall.state;

import me.coderdan.fall.Game;
import me.coderdan.fall.util.Graphics;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class MainMenu extends State
{
	
	// Start Transition.
	private int x = 255;
	private int y = 10;
	private boolean bounced = false;
	private boolean landed = false;
	private boolean comingUp = false;
	private boolean doneTrans = false;
	
	// Menu.
	private int selected = 0;
	private int options = 3;
	
	// Fonts.
	private TrueTypeFont ARIAL_40;
	private TrueTypeFont ARIAL_20;
	
	public void init()
	{
		// Initialize fonts.
		ARIAL_40 = new TrueTypeFont(new Font("Arial", Font.PLAIN, 40), true);
		ARIAL_20 = new TrueTypeFont(new Font("Arial", Font.PLAIN, 20), true);
	}
	
	public void update()
	{
		if(!bounced && !landed)
		{
			y += 4.25;
			if(y == 34)
				landed = true;
		}
		if(!bounced && landed)
		{
			y -= 3;
			x -= 0.25;
			if(y == 4)
				bounced = true;
		}
		if(bounced && landed && !comingUp)
		{
			y += 4.25;
			x -= 0.25;
			if(y == 444)
			{
				landed = true;
				comingUp = true;
			}
		}
		if(bounced && landed && comingUp && !doneTrans)
		{
			x++;
			y -= 5;
			if(y == 34)
			{
				doneTrans = true;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			if(selected == 0)
				selected = options;
			else
				selected--;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			if(selected == options)
				selected = 0;
			else
				selected++;
		}
		if(Keyboard.isKeyDown(28)) // enter key
		{
			if(selected == 0)
			{
				StateManager.setState(Play.getInstance());
			}
			if(selected == 1)
			{
				System.out.println("Highscores");
			}
			if(selected == 2)
			{
				StateManager.setState(Credits.getInstance());
			}
			if(selected == 3)
			{
				Game.closeApp();
			}
		}
	}

	public void render()
	{
		// title
		me.coderdan.fall.util.Graphics.drawText("Fall", ARIAL_40, Color.red, x, y);
		
		// menu items
		addMenuItem("Play", 0);
		addMenuItem("Highscores", 1);
		addMenuItem("Credits", 2);
		addMenuItem("Quit", 3);
	}
	
	private void addMenuItem(String name, int location)
	{
		Color color;
		if(selected == location)
			color = Color.cyan;
		else
			color = Color.blue;
		
		int x = 10;
		int y = 200 + location * 25;
		Graphics.drawText(name, ARIAL_20, color, x, y);
	}
	
	public int getID()
	{
		return StateManager.MAIN_MENU;
	}
	
	
	public static MainMenu getInstance()
	{
		return new MainMenu();
	}
	
}
