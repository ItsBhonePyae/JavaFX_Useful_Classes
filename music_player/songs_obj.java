package music_player;

import java.io.File;

public class songs_obj 
{
	private File [] song;

	public File[] getsong() 
	{
		return song;
	}

	public void setsong(File[] song) 
	{
		this.song = song;
	}
	
	public void setsong(String filepath) 
	{
		this.song = new File(filepath).listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
	}
	
	public void getsong_list() 
	{
		for(File s : song) 
		{
			System.out.println(s.getName());
		}
	}

	public songs_obj(File[] song) 
	{
		this.song = song;
	}
	
	public songs_obj(String filepath) 
	{
		this.song = new File(filepath).listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
	}
	
	public songs_obj() 
	{
		this.song = new File("E:\\song\\blue archive").listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
	}
	
	
	
}
