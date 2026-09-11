package Draggable_Objects;

import javafx.scene.Cursor;
import javafx.scene.Node;

public class Make_Draggable {

	Node node;
	
	private static class Coordinate{
		double x,y;
	}	
	
	public Make_Draggable(Node node) {
		this.node = node;
	}
	
	public void drag_event() {
		final Coordinate drag = new Coordinate();	
		node.setOnMousePressed(mouse ->{
			
			drag.x = node.getLayoutX() - mouse.getSceneX();
			drag.y = node.getLayoutY() - mouse.getSceneY();
			node.setCursor(Cursor.MOVE);
		});
		
		node.setOnMouseDragged( mouse -> {
			node.setLayoutX(mouse.getSceneX()+drag.x);
			node.setLayoutY(mouse.getSceneY()+drag.y);
			
		});
		
		node.setOnMouseReleased(mouse -> {
			node.setCursor(Cursor.HAND);
		});
	}
	
	
}
