package ru.mipt.agents.agent;

import ru.mipt.agents.AbstractAgent;
import ru.mipt.agents.agent.controller.DecisionController;
import ru.mipt.agents.agent.controller.EnergyController;
import ru.mipt.agents.agent.controller.PositionController;
import ru.mipt.agents.model.Signal;
import ru.mipt.controller.CommunicationController;

public class Agent extends AbstractAgent
{

	private DecisionController decisionController = new DecisionController(this);

	private EnergyController energyController = new EnergyController(this);

	private PositionController positionController = new PositionController(this);

	public Agent(Double energy, Double size, Double visibleRadius, Double audibleRadius)
	{
		super(energy, size, visibleRadius, audibleRadius);
	}

	/**
	 * Element Interface
	 */
	@Override
	public void setup()
	{
//		size = new Random(Settings.Agent.MAX_START_SIZE, Settings.Agent.MIN_START_SIZE).aDouble();
//		energy = new Random(Settings.Agent.MAX_START_ENERGY, Settings.Agent.MIN_START_ENERGY).aDouble();
//		position = new Point((double) (Settings.WORLD_WIDTH / 2), (double) (Settings.WORLD_HEIGHT / 2));

//		super.setup();
//		decisionController.initialize();
//		energyController.initialize();
//		positionController.initialize();
	}




	/**
	 * Getters and setters
	 */
	public CommunicationController getCommunicationController()
	{
		return communicationController;
	}

}
