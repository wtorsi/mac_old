package ru.mipt;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.mipt.world.World;


public class Main extends Application
{

	World world = new World(60, "Agents world");

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		world.initialize(primaryStage);
		world.startLoop();

		primaryStage.show();
	}

}
