package me.coderdan.fall.state;

public class StateManager
{

	// Current State.
	private static State STATE;
	
	// State ID'S.
	public static final int MAIN_MENU = 0;
	public static final int PLAY = 1;
	public static final int HIGHSCORES = 2;
	public static final int CREDITS = 3;
	
	public static void setState(State state)
	{
		System.out.println("Changing the state to state ID " + state.getID());
		STATE = state;
		STATE.init();
		System.out.println("Initialized the state.");
	}
	
	public static State getState()
	{
		return STATE;
	}
	
}
