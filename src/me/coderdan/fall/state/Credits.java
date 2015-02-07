package me.coderdan.fall.state;

import me.coderdan.fall.Game;
import me.coderdan.fall.Graphics;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Credits extends State
{
	
	private TrueTypeFont ARIAL_26;
	
	private List<String> creds;
	private float camera = 300;
	
	public void init()
	{
		creds = new ArrayList<String>();
        creds.add("-= Fall =-");
		creds.add("Credits to");
		creds.add("");
		
		try
		{
			
			File tmpFile = File.createTempFile("NOoooooo-RESOURCE-credits", ".tmp");
			FileOutputStream out = new FileOutputStream(tmpFile);
			InputStream in = Game.class.getResourceAsStream("/res/credits.txt");
			
			byte[] buffer = new byte[5120];
			int length = 0;
			while((length = in.read(buffer)) != -1)
				out.write(buffer, 0, length);
		
			out.close();
			in.close();
			
			Game.tempFiles.add(tmpFile);
			
			BufferedReader reader = new BufferedReader(new FileReader(tmpFile));
			String line;
			while((line = reader.readLine()) != null)
				creds.add(line);

			reader.close();
			
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		ARIAL_26 = new TrueTypeFont(new Font("Arial", Font.PLAIN, 26), true);
	}

	public void update()
	{
		camera--;
		if(camera < (0 - (creds.size() * 26)))
			StateManager.setState(MainMenu.getInstance());
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			StateManager.setState(MainMenu.getInstance());
	}

	public void render()
	{
		int index = 0;
		for(String cred : creds)
		{
			Graphics.drawText(cred, ARIAL_26, Color.cyan, 10, camera + (index * 26));
            index++;
		}
	}

	public int getID()
	{
		return StateManager.CREDITS;
	}
	
	
	public static Credits getInstance()
	{
		return new Credits();
	}

}
