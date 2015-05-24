package ru.mipt.agents.agent.model;

import ru.mipt.system.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Target
{
	private Point position;

	private Importance importance;

	private boolean reached = false;

	private Circle sceneNode = new Circle(1, Color.web("red"));

	public Target(Point position, Importance importance)
	{
		this.position = position;
		this.importance = importance;

	}

	public Target(Point position)
	{
		this.position = position;
	}

	public Point getPosition()
	{
		return position;
	}

	public Importance getImportance()
	{
		return importance;
	}
	
	public boolean isReached()
	{
		return reached;
	}
	
	public enum Importance
	{
		RANDOM(0),
		DISCOVER(5),
		AUDIBLE(10),
		VISIBLE(15);

		private final int value;

		private Importance(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}

	}

	public void setReached(boolean reached)
	{
		this.reached = reached;
		sceneNode.setFill(Color.web("green"));
	}

	public Circle getSceneNode()
	{
		return sceneNode;
	}

	@Override
	public String toString()
	{
		return "Target{" +
				"position=" + position +
				'}';
	}
}
