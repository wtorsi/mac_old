package ru.mipt.agents.agent.controller;

import ru.mipt.controller.AbstractController;
import ru.mipt.agents.agent.Agent;

public class StorageController extends AbstractController
{

	public StorageController(Agent agent)
	{
		super(agent);
	}

	@Override
	public Agent getElement()
	{
		return (Agent) super.getElement();
	}
}
