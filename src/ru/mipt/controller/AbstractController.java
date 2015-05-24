package ru.mipt.controller;

import ru.mipt.agents.AbstractAgent;

public abstract class AbstractController
{
	protected AbstractAgent agent;

	public AbstractController(AbstractAgent agent)
	{
		this.agent = agent;
	}

	public AbstractAgent getAgent()
	{
		return agent;
	}
}
