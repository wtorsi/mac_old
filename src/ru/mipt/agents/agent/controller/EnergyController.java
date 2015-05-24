package ru.mipt.agents.agent.controller;

import ru.mipt.controller.AbstractController;
import ru.mipt.agents.agent.Agent;
import ru.mipt.system.Random;
import ru.mipt.system.Settings;

public class EnergyController extends AbstractController
{
	private double energy;

	public EnergyController(Agent agent)
	{
		super(agent);
	}

	@Override
	public void initialize()
	{
		super.initialize();
		energy = new Random(Settings.Agent.MAX_START_ENERGY, Settings.Agent.MIN_START_ENERGY).aDouble();
	}



	public boolean isAlive()
	{
		return energy >= Settings.Agent.MIN_ENERGY;
	}

	public void expend()
	{
		energy *= (1 - Settings.Agent.DECREASE_MULTIPLIER);
	}
	
	public double getEnergy()
	{
		return energy;
	}
}
