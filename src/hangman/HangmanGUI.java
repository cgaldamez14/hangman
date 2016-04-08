package hangman;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HangmanGUI extends Application {
	
	@Override
	public void start(Stage primaryStage){
		BorderPane bp = new BorderPane();
		HangmanPane hp = new HangmanPane();
		
		bp.getStylesheets().add("styles/guiStyle.css");
		bp.getStyleClass().add("background");
		
		startWindow(primaryStage,bp,hp);
		
		Scene scene = new Scene(bp, 350, 350);
		primaryStage.setResizable(false);
		primaryStage.setTitle("HangMan");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void startWindow(Stage primaryStage, BorderPane bp, HangmanPane hp){
		VBox vb = new VBox(10);

		Label hangMan = new Label("HangMan");
		hangMan.getStyleClass().add("hangManText");
		Button btn = new Button("Start");
		btn.getStyleClass().add("endButtons");

		btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event){
				bp.getChildren().clear();
				primaryStage.setWidth(700);
				primaryStage.setHeight(700);
				gameWindow(bp,hp);
			}
		});

		vb.getStyleClass().addAll("center");
		vb.getChildren().addAll(hangMan, btn);

		bp.setCenter(vb);
	}

	public static void gameWindow(BorderPane bp, HangmanPane hp){
		bp.getStyleClass().clear();
		bp.getStyleClass().add("root");
		bp.getChildren().clear();

		hp.paint();
		bp.setCenter(hp);

		RandomWordGenerator rwg = new RandomWordGenerator();

		newWord(bp,rwg,hp);
	}



	public static void newWord(BorderPane bp, RandomWordGenerator rwg, HangmanPane hp){
		WordTracker rw = new WordTracker(rwg,hp);
		System.out.println(rwg.getWord());
		System.out.println(rw.getWordLength());
		
		bp.getStyleClass().clear();
		bp.getStyleClass().add("root");
		
		VBox vb1 = new VBox();
		MenuBar menu = menuBar(bp,hp);
		
		HBox hb1 = new HBox();
		Label hangMan = new Label("HangMan");
		hangMan.getStyleClass().add("hangManText");
		hb1.getStyleClass().addAll("background", "center");
		hb1.getChildren().add(hangMan);
		
		vb1.getChildren().addAll(menu,hb1);
		
		bp.setTop(vb1);
		
		VBox vb = new VBox(14);
		Label wordLabel = new Label("Word");
		wordLabel.getStyleClass().add("titleText");
		Label word = new Label(rw.getWord());
		word.getStyleClass().add("regularText");
		Label incorrectGuesses = new Label("Wrong Guesses");
		incorrectGuesses.getStyleClass().add("titleText");
		Label wrong = new Label(rw.getIncorrectList());
		wrong.getStyleClass().add("regularText");
		vb.getStyleClass().addAll("background","center", "paddingHorizontal");
		vb.getChildren().addAll(wordLabel, word, incorrectGuesses, wrong);
		bp.setRight(vb);
		
		HBox hb2 = new HBox(10);
		Button btn = new Button("Check");
		btn.getStyleClass().add("endButtons");
		TextField tf = new TextField();
		hb2.getStyleClass().addAll("background","center", "paddingVertical");
		hb2.getChildren().addAll(tf,btn);

		btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event){
				rw.letterGuess(tf.getText());
				tf.clear();
				hp.paint();
				word.setText(rw.getWord());
				wrong.setText(rw.getIncorrectList().toString());
				
				if(hp.gameOver() || rwg.noMoreWords()){
					gameOverWindow(bp,rwg,hp);
				}
				
				if(rw.wordGuessed()){
					wordGuessedWindow(bp,rwg,hp);
				}
			}
		});
		
		bp.setBottom(hb2);
	}
	
	public static void gameOverWindow(BorderPane bp, RandomWordGenerator rwg,HangmanPane hp){
		
		bp.getChildren().clear();
		
		MenuBar menu = menuBar(bp,hp);
		bp.setTop(menu);
		
		VBox vb = new VBox(12);
		Label gameOver = new Label("Game Over!");
		
		
		HBox hb = new HBox(10);
		Button btnNew = new Button("New Word");
		Button btnExit = new Button("Quit");
		btnNew.getStyleClass().add("endButtons");
		btnExit.getStyleClass().add("endButtons");
		hb.getChildren().addAll(btnNew,btnExit);
		hb.getStyleClass().addAll("center");
		
		gameOver.getStyleClass().add("hangManText");
		vb.getStyleClass().addAll("center");
		
		vb.getChildren().addAll(gameOver,hb);
		bp.setCenter(vb);
		
		
		btnNew.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event){
				RandomWordGenerator newRwg = new RandomWordGenerator();
				bp.getChildren().clear();
				hp.paint();
				bp.setCenter(hp);
				newWord(bp,newRwg,hp);
				}
		});
		
		btnExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event){
					System.exit(0);
				}
		});
	}
	
	public static void wordGuessedWindow(BorderPane bp, RandomWordGenerator rwg,HangmanPane hp){
		
		bp.getChildren().clear();
		
		MenuBar menu = menuBar(bp,hp);
		bp.setTop(menu);
		
		VBox vb = new VBox(12);
		Label gotIt = new Label("You Got It!");
		
		
		HBox hb = new HBox(10);
		Button btnNew = new Button("Next Word");
		Button btnExit = new Button("Quit");
		btnNew.getStyleClass().add("endButtons");
		btnExit.getStyleClass().add("endButtons");
		hb.getChildren().addAll(btnNew,btnExit);
		hb.getStyleClass().addAll("center");
		
		
		gotIt.getStyleClass().add("hangManText");
		vb.getStyleClass().addAll("center");
		vb.getChildren().addAll(gotIt,hb);
		bp.setCenter(vb);
		
		
		btnNew.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event){
				bp.getChildren().clear();
				hp.setNumberWrong(0);
				hp.paint();
				bp.setCenter(hp);
				newWord(bp,rwg,hp);
				}
		});
		
		btnExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event){
					System.exit(0);
				}
		});
	}
	
	public static MenuBar menuBar(BorderPane bp, HangmanPane hp) {
		MenuBar menuBar = new MenuBar();
		menuBar.getStyleClass().add("menu_bar");
		Menu fileMenu = new Menu("File");

		MenuItem newGameItem = new MenuItem("New Game");
		newGameItem.setOnAction(e -> {
			RandomWordGenerator newRwg = new RandomWordGenerator();
			bp.getChildren().clear();
			hp.paint();
			bp.setCenter(hp);
			newWord(bp,newRwg,hp);
		});
		
		MenuItem quitItem = new MenuItem("Quit");
		quitItem.setOnAction(e -> {
			System.exit(0);
		});

		quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q,KeyCombination.CONTROL_DOWN));

		fileMenu.getItems().addAll(newGameItem,quitItem);
		menuBar.getMenus().add(fileMenu);
		return menuBar;
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
