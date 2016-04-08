package hangman;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class HangmanPane extends Pane{
	
	private int numberWrong = 0;
	
	public HangmanPane(){}
	
	public void paint(){
		widthProperty().addListener(o ->paint());
		heightProperty().addListener(o -> paint());
		
		this.getChildren().clear();
		
		Line line1 = new Line(getWidth()/4, (getHeight()/5) * 3.5,getWidth()/4, getHeight()/5 - 40);
		Line line2 = new Line(getWidth()/4, getHeight()/5 - 40,getWidth()/2 + 50, getHeight()/5 - 40);
		Line line3 = new Line(getWidth()/2 + 50, getHeight()/5 - 40,getWidth()/2 + 50, getHeight()/3 - 70);
		Line line4 = new Line(getWidth()/4, (getHeight()/5) * 3.5,getWidth()/2 + 70, (getHeight()/5) * 3.5);
		Line line5 = new Line(getWidth()/2 + 70, (getHeight()/5) * 4.5,getWidth()/2 + 70, (getHeight()/5) * 3.5);
		Line line6 = new Line(getWidth()/4, (getHeight()/5) * 3.5,getWidth()/6, (getHeight()/5) * 3.5);
		Line line7 = new Line(getWidth()/6, (getHeight()/5) * 4.5,getWidth()/6, (getHeight()/5) * 3.5);

		
		line1.setStroke(Color.WHITE);
		line2.setStroke(Color.WHITE);
		line3.setStroke(Color.WHITE);
		line4.setStroke(Color.WHITE);
		line5.setStroke(Color.WHITE);
		line6.setStroke(Color.WHITE);
		line7.setStroke(Color.WHITE);

		this.getChildren().addAll(line1, line2, line3, line4, line5, line6, line7);
		switch(numberWrong){
		case 6:		this.leftLeg();
		case 5:		this.rightLeg();
		case 4:		this.leftArm();
		case 3:		this.rightArm();
		case 2:		this.torso();
		case 1:		this.head();
		}
	}
	
	private void head(){
		Circle c = new Circle(getWidth()/2 + 50,getHeight()/3 - 45,30);
		c.setFill(Color.WHITE);
		this.getChildren().add(c);
	}
	
	private void torso(){
		Line torso = new Line(getWidth()/2 + 50, getHeight()/2 + 20,getWidth()/2 + 50, getHeight()/3 - 30);
		torso.setStroke(Color.WHITE);
		this.getChildren().add(torso);
	}
	
	private void rightArm(){
		Line rightArm = new Line(getWidth()/2 + 50, getHeight()/2 - 60,getWidth()/2 - 20, getHeight()/2 - 80);
		rightArm.setStroke(Color.WHITE);
		this.getChildren().add(rightArm);
	}
	
	private void leftArm(){
		Line leftArm = new Line(getWidth()/2 + 50, getHeight()/2 - 60,getWidth()/2 + 120, getHeight()/2 - 80);
		leftArm.setStroke(Color.WHITE);
		this.getChildren().add(leftArm);
	}
	
	private void rightLeg(){
		Line rightLeg = new Line(getWidth()/2 + 50, getHeight()/2 + 20,getWidth()/2 - 20, getHeight()/2 + 60);
		rightLeg.setStroke(Color.WHITE);
		this.getChildren().add(rightLeg);
	}
	
	private void leftLeg(){
		Line leftLeg = new Line(getWidth()/2 + 50, getHeight()/2 + 20,getWidth()/2 + 120, getHeight()/2 + 60);
		leftLeg.setStroke(Color.WHITE);
		this.getChildren().add(leftLeg);
	}
	
	public boolean gameOver(){
		if(numberWrong == 7){
			setNumberWrong(0);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setNumberWrong(int wrong){
		numberWrong = wrong;
	}
}
