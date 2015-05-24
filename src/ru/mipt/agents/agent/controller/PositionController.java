package ru.mipt.agents.agent.controller;

import ru.mipt.controller.AbstractController;
import ru.mipt.agents.agent.Agent;
import ru.mipt.system.Point;
import ru.mipt.system.Random;
import ru.mipt.system.Segment;
import ru.mipt.system.Settings;

public class PositionController extends AbstractController
{

	private double speed;


	public PositionController(Agent agent)
	{
		super(agent);
	}

	@Override
	public Agent getElement()
	{
		return (Agent) super.getElement();
	}

	@Override
	public void initialize()
	{
		super.initialize();
		speed = new Random(Settings.Agent.MAX_SPEED, Settings.Agent.MIN_SPEED).aDouble();
	}


	public void update()
	{
		Point position = getElement().getDecisionController().getDestinationPosition();
		if (position != null) {
			move(position);
		}
	}


	private void move(Point targetPosition)
	{
		getElement().setPosition(getElement().getPosition().add(targetPosition.subtract(
				getElement().getPosition()).multiply(speed)));
	}


	public boolean isInside(Point center, Double minDistance, Double maxDistance)
	{

		Double distance = getElement().getPosition().subtract(center).magnitude();

		return (distance <= maxDistance && distance >= minDistance);
	}

	public boolean isInside(Segment segment)
	{
		return segment.contains(getElement().getPosition());
	}

	public boolean isInside(Point center, Double maxDistance)
	{

		return isInside(center, 0.0, maxDistance);
	}

}
