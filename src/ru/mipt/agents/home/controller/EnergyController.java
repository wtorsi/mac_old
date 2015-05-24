package ru.mipt.agents.home.controller;

import ru.mipt.controller.AbstractController;
import ru.mipt.agents.home.Home;
import ru.mipt.system.Random;
import ru.mipt.system.Settings;

public class EnergyController extends AbstractController
{

	private double energy;

	public EnergyController(Home home)
	{
		super(home);
	}

	@Override
	public void initialize()
	{
		super.initialize();
		energy = new Random(Settings.Home.MAX_START_ENERGY, Settings.Home.MIN_START_ENERGY).aDouble();

	}

	public boolean isAlive()
	{

		return energy > Settings.Home.MIN_ALIVE_ENERGY;
	}

	public void expend()
	{
		energy *= (1 - Settings.Home.ENERGY_EXPEND_SPEED);
	}
}
