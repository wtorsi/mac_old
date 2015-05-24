package ru.mipt.agents.model;

import ru.mipt.system.Point;

public class Signal
{
	private String id;
	private Point position;

	public Signal(String id, Point position)
	{
		this.id = id;
		this.position = position;
	}

	public String getId()
	{
		return id;
	}

	public Point getPosition()
	{
		return position;
	}
}
