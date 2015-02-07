package me.coderdan.fall;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.util.HashMap;

public class Player
{

	private static String playingName;
	private static HashMap<String, Boolean> shouldPlay = new HashMap<String, Boolean>();
	private static HashMap<String, AdvancedPlayer> players = new HashMap<String, AdvancedPlayer>();
	
	public static void loop(final String name)
	{	
		try
		{
			if(name.equals(playingName))
				return;
			else
				shouldPlay.put(playingName, false);
			
			AdvancedPlayer player = new AdvancedPlayer(Game.class.getResourceAsStream("/res/music/" + name + ".mp3"));
			player.setPlayBackListener(new PlaybackListener()
			{
				public void playbackFinished(PlaybackEvent e)
				{
					try
					{
						e.setFrame(0);
						AdvancedPlayer player = new AdvancedPlayer(Game.class.getResourceAsStream("/res/music/" + playingName + ".mp3"));
						player.play();
					} catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			});
			players.put(name, player);
			new Thread(new Runnable()
			{
				public void run()
				{
					try
					{
						playingName = name;
						shouldPlay.put(name, true);
						players.get(name).play();
					} catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable()
			{
				public void run()
				{
					while(shouldPlay.get(name))
                    {
						try { Thread.sleep(1000); } catch (Exception ex) { }
					}
					players.get(name).close();
				}
			}).start();
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
}
