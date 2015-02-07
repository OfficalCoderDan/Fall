package me.coderdan.fall.state;

import me.coderdan.fall.Graphics;
import me.coderdan.fall.game.Player;
import me.coderdan.fall.game.World;
import me.coderdan.fall.render.*;
import me.coderdan.fall.render.Button;
import me.coderdan.fall.util.*;
import me.coderdan.fall.util.Point;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.util.Random;

public class Play extends State
{

	private Player player;
	private World world;
	private Random r;
	private boolean d = false;

    // Buttons
    private me.coderdan.fall.render.Button button;

	// Fonts
	private TrueTypeFont COMIC_SANS_MS_20;
	
	public void init()
	{
		this.player = new Player();
		this.world = new World(player);
		
		// Initialize Fonts
		COMIC_SANS_MS_20 = new TrueTypeFont(new Font("Comic Sans MS", Font.PLAIN, 20), true);

        // Initialize Buttons
        button = new Button(Color.white, "Button!", 100, 100, new Point(20, 20));

		r = new Random();
	}

	public void update()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			player.left();
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			player.right();
		}
		
		player.down();
		world.update();
		
		if(!player.gameOver)
			player.score += 0.1;
		
		if(!d)
		{
			if(player.score > 1000)
			{ 
				d = true;
				int col = r.nextInt(7);
				
				switch(col)
				{
					case 0:
						player.colour = Color.blue;
						break;
					case 1:
						player.colour = Color.magenta;
						break;
					case 2:
						player.colour = Color.pink;
						break;
					case 3:
						player.colour = Color.red;
						break;
					case 4:
						player.colour = Color.orange;
						break;
					case 5:
						player.colour = Color.yellow;
						break;
					case 6:
						player.colour = Color.green;
						break;
				}
			}
		}
		else
			d = true;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			StateManager.setState(MainMenu.getInstance());
		
	}

	public void render()
	{
        button.draw(Color.black);

		Graphics.init2d();
		world.draw();
		player.draw();
	
		Graphics.initTexture2d();
		Graphics.drawText("Score: " + ((int) Math.floor(player.score)), COMIC_SANS_MS_20, Color.red, 550, 100);
	}
	
	public int getID()
	{
		return StateManager.PLAY;
	}
	
	public static Play getInstance()
	{
		return new Play();
	}
	
}
