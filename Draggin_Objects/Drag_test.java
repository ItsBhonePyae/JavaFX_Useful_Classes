package Draggable_Objects;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Drag_test extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	/*
	 * private static class Coordinate{ double x,y; }
	 * 
	 * public void makeDraggable(Node node) { final Coordinate drag = new
	 * Coordinate();
	 * 
	 * node.setOnMousePressed(mouse ->{
	 * 
	 * drag.x = node.getLayoutX() - mouse.getSceneX(); drag.y = node.getLayoutY() -
	 * mouse.getSceneY(); node.setCursor(Cursor.MOVE); });
	 * 
	 * node.setOnMouseDragged( mouse -> { node.setLayoutX(mouse.getSceneX()+drag.x);
	 * node.setLayoutY(mouse.getSceneY()+drag.y);
	 * 
	 * });
	 * 
	 * node.setOnMouseDragReleased(mouse -> { node.setCursor(Cursor.HAND); }); }
	 */
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Pane root = new Pane();
		
		Rectangle box = new Rectangle(200,100);
		box.setCursor(Cursor.HAND);
		box.setLayoutX(150);
		box.setLayoutY(150);
		
		Make_Draggable md = new Make_Draggable(box);
		md.drag_event();
		
		
		root.getChildren().addAll(box);
		
		Scene scene = new Scene(root,500,500);
		
		stage.setScene(scene);
		stage.setTitle("Drag Test");
		stage.show();
		
		
	}

}
