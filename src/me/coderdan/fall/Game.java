package me.coderdan.fall;

import me.coderdan.fall.state.MainMenu;
import me.coderdan.fall.state.StateManager;
import me.coderdan.fall.util.Player;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Game
{
	
	// Window data.
	private static final String VERSION = "0.4.1";
	private static final String VERSION_TYPE = "ALPHA";
	private static final String WINDOW_TITLE = "Fall v" + VERSION + " " + VERSION_TYPE;
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 400;
	
	// Extra data.
	public static List<File> tempFiles = new ArrayList<File>();
	
	// Run the game.
	public static void run()
	{
		System.out.println("Running game..");
		init();
		loop();
	}
	
	// Initialize the game.
	private static void init()
	{
		System.out.println("Initializing game..");
		try
		{
			// Setup the display and create it.
			Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
			Display.setTitle(WINDOW_TITLE);
			Display.create();
			System.out.println("Created the display.");
		} catch (LWJGLException ex)
		{
			System.out.println("Error creating the display. (Read more below)");
			ex.printStackTrace();
		}
		
		// Initialization of OpenGL.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	    glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	    System.out.println("Initialized OpenGL.");
	    
	    StateManager.setState(MainMenu.getInstance());
	}
	
	// Main game loop.
	private static void loop()
	{
		System.out.println("Starting game loop..");
		// While loop until the exit button is pressed.
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			tick();
			render();
			
			int stateID = StateManager.getState().getID();
			if(		stateID == StateManager.MAIN_MENU ||
					stateID == StateManager.HIGHSCORES ||
					stateID == StateManager.CREDITS)
				Player.loop("Mellowtron");
			if(stateID == StateManager.PLAY)
				Player.loop("Ouroboros");
			
			Display.update();
			Display.sync(30);
		}
		
		// End the application.
		closeApp();
	}
	
	private static void tick()
	{
		StateManager.getState().update();
	}
	
	private static void render()
	{
		StateManager.getState().render();
	}
	
	public static void closeApp()
	{
		Display.destroy();
		System.out.println("Exiting application..");
		for(File file : tempFiles)
			file.delete();
		System.exit(0);
	}
	
	// Entry point.
	public static void main(String[] args)
	{
		run();
	}
		
}
