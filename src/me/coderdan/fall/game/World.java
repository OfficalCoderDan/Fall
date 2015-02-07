package me.coderdan.fall.game;

import me.coderdan.fall.Graphics;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class World
{
	
	private Player p;
	private boolean drawnStart = false;
	private HashMap<Integer, String[]> world = new HashMap<Integer, String[]>();
	private List<Rectangle> rects = new ArrayList<Rectangle>();
	
	public World(Player p)
	{
		this.p = p;
		generate(100);
	}
	
	public void clear()
	{
		System.out.println("Clearing world..");
		world.clear();
		rects.clear();
		p.getPoint().set(Player.X, Player.Y);
	}
	
	public void generate(int amount)
	{
		System.out.println("Generating " + amount + " blocks for the world.");
		
		if(!drawnStart)
		{
			// Nothing for 3
			String[] nothing = new String[]
			{
				"true:150",
				"false:350",
				"true:300"
			};
			world.put(0, nothing);
			world.put(1, nothing);
			world.put(2, nothing);
			world.put(3, nothing);
			drawnStart = true;
		}
		
		// Generation
		Random r = new Random();
		amount += world.size();
		for(int i = world.size(); i <= (amount); i++)
		{
			String[] pd;
			int next = -1;
			int td = -1;
			while(td != 9)
			{
				td = r.nextInt(25);
				
				if(next < 100)
					td = -1;
				
				next++;
			}
			next *= 1.5;
			pd = new String[]
			{
				"true:" + (next - 1),
				"false:" + next,
				"true:" + (700 - next)
			};
			
			Rectangle rect = new Rectangle(0, i*30, (next - 1), 30);
			Rectangle rect1 = new Rectangle(((next - 1) + next), i*30, (700 - next), 30);
			rects.add(rect);
			rects.add(rect1);
			
			world.put(i, pd);
		}
		System.out.println("Done generating world.");
	}
	
	public void draw()
	{
		for(Integer key : world.keySet())
		{
			float y = key * 30;
			float last = 0; 
			for(String data : world.get(key))
			{
				boolean v = Boolean.parseBoolean(data.split(":")[0]);
				float a = Float.parseFloat(data.split(":")[1]);
				
				if(v)
					Graphics.drawRect(Color.cyan, a, 30, last, y - p.getPoint().getY());
				last += a;
			}
		}
	}
	
	public void update()
	{
		if(p.getPoint().getY() >= (world.size() * 30 - 1200))
			generate(100);

		for(Rectangle r : rects)
		{			
			if(p.getRect().intersects(r))
				p.gameOver();
		}
	}
	
}
