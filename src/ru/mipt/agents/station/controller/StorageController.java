package ru.mipt.agents.station.controller;

import ru.mipt.controller.AbstractController;
import ru.mipt.agents.AbstractAgent;
import ru.mipt.system.Random;
import ru.mipt.system.Settings;

public class StorageController extends AbstractController
{

	private Double storage;

	public StorageController(AbstractAgent element)
	{
		super(element);
	}

	@Override
	public void initialize()
	{
		super.initialize();

		storage = new Random(Settings.EnergyStorage.MAX_ENERGY, Settings.EnergyStorage.MIN_ENERGY).aDouble();
	}

	public void expand()
	{

	}
}
