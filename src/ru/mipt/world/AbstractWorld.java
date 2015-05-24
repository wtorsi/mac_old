package ru.mipt.world;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.mipt.container.ContainerAware;

public abstract class AbstractWorld extends ContainerAware
{

	/**
	 * Frames per second
	 */
	private final int framesPerSecond;
	/**
	 * Window title
	 */
	private final String windowTitle;
	/**
	 * Surface of the world
	 */
	private Scene worldSurface;
	/**
	 * Group of scene nodes
	 */
	private Group layout;
	/**
	 * Main timeline of the app
	 */
	private Timeline timeline = new Timeline();


	public AbstractWorld(final int fps, final String title)
	{
		framesPerSecond = fps;
		windowTitle = title;

		// create and set timeline for the game loop
		init();
	}

	/**
	 * The game loop (Timeline) which is used to update, check collisions, and
	 * cleanup sprite objects at every interval (fps).
	 *
	 * @return Timeline An animation running indefinitely representing the game
	 * loop.
	 */
	protected Timeline getTimeline()
	{
		return timeline;
	}

	public void init()
	{

		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);

		final Duration oneFrameAmt = Duration.millis(1000 / (float) getFramesPerSecond());
		final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
				new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						preUpdateAgents();
						updateAgents();
						updateViews();
					}
				});
//

		timeline.getKeyFrames().add(oneFrame);

	}


	/**
	 * Initialize  world
	 */
	public abstract void initialize(final Stage primaryStage);

	/**
	 * Starts timeline loop
	 */
	public void startLoop()
	{
		timeline.play();
	}


	/**
	 * Update agents
	 */
	abstract protected void preUpdateAgents();

	/**
	 * Update agents
	 */
	abstract protected void updateAgents();

	/**
	 * Update views
	 */
	abstract protected void updateViews();


	/**
	 * @return int frames
	 */
	protected int getFramesPerSecond()
	{
		return framesPerSecond;
	}

	/**
	 * Returns the game's window title.
	 *
	 * @return String The game's window title.
	 */
	public String getWindowTitle()
	{
		return windowTitle;
	}

	/**
	 * @return worldSurface Scene
	 */
	public Scene getWorldSurface()
	{
		return worldSurface;
	}


	public AbstractWorld setWorldSurface(Scene worldSurface)
	{
		this.worldSurface = worldSurface;

		return this;
	}

	public Group getLayout()
	{
		return layout;
	}


	public void setLayout(Group layout)
	{
		this.layout = layout;
	}

}
