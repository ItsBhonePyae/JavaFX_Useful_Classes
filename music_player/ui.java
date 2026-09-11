package music_player;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ui extends Application
{

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox p = new VBox();
		
		media_back mp = new media_back();
		
		
		
		Button playButton = new Button("Play");
		
		Button filechoser = new Button("file");
		
		filechoser.setOnAction(e ->
		{
			DirectoryChooser chooser = new DirectoryChooser();

			chooser.setTitle("choose file");
			
			File folder = chooser.showDialog(stage);
			
			if (folder != null) {
		        System.out.println(folder.getAbsolutePath());
		    }
			
			songs_obj songs = new songs_obj(folder.getAbsolutePath());
			
			mp.setsong(songs);
			mp.getsong().getsong_list();
			
		});
		
		playButton.setOnAction(e->
		{
			
			mp.media_player(mp.song_play("ブルーアーカイブ Blue Archive OST 104. Cotton Candy Island(MP3_160K).mp3")); //make it dynamic
		});
		
		p.getChildren().addAll(playButton, filechoser);
		
		
		Scene scene = new Scene(p, 600, 800); 
		
		stage.setScene(scene);
		stage.setTitle("play");
		stage.show();
	}

}
