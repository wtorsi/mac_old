package ru.mipt.agents.agent.model;

import ru.mipt.system.Point;

public class Leader
{

	private String id;
	private Double energy;
	private Point position;
	private Integer resolvedAgentsSize;

	public String getId()
	{
		return id;
	}

	public Leader setId(String id)
	{
		this.id = id;
		return this;

	}

	public Point getPosition()
	{
		return position;
	}

	public Leader setPosition(Point position)
	{
		this.position = position;
		return this;

	}

	public Integer getResolvedAgentsSize()
	{
		return resolvedAgentsSize;
	}

	public Leader setResolvedAgentsSize(Integer resolvedElementsSize)
	{
		this.resolvedAgentsSize = resolvedElementsSize;

		return this;
	}

	public Double getEnergy()
	{
		return energy;
	}

	public Leader setEnergy(Double energy)
	{
		this.energy = energy;
		return this;

	}
}