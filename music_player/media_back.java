package music_player;

import java.io.File;

import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;

public class media_back 
{

	songs_obj songs = new songs_obj();
	
	public File song_play(String name) 
	{
		for(File s : songs.getsong()) 
		{
			if(s.getName().equals(name)) 
			{
				return s;
			}
		}
		return songs.getsong()[1];
	}
	
	public void media_player(File song) 
	{
		Media media = new Media(song.toURI().toString());
		
		MediaPlayer player = new MediaPlayer(media);
		
		player.play();
	}
	public media_back() 
	{
		
	}
	public media_back(songs_obj songs) 
	{
		this.songs = songs;
	}
	
	public void setsong(songs_obj songs) 
	{
		this.songs = songs;
	}
	public songs_obj getsong() 
	{
		return this.songs;
	}
}
