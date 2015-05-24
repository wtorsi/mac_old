package ru.mipt.agents.agent.model;

import ru.mipt.system.Point;

public class Home
{
	private String id;
	private Double size;
	private Point position;
	private Double receiverRadius;


	public String getId()
	{
		return id;
	}

	public Home setId(String id)
	{
		this.id = id;

		return this;
	}

	public Double getSize()
	{
		return size;
	}

	public Home setSize(Double size)
	{
		this.size = size;
		return this;

	}

	public Point getPosition()
	{
		return position;
	}

	public Home setPosition(Point position)
	{
		this.position = position;
		return this;

	}

	public Double getReceiverRadius()
	{
		return receiverRadius;
	}


	public void setReceiverRadius(Double receiverRadius)
	{
		this.receiverRadius = receiverRadius;
	}


}
