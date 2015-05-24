package ru.mipt.agents.agent.manager;

import ru.mipt.agents.agent.Agent;

abstract public class AbstractManager
{
	protected Agent element;

	public AbstractManager(Agent element)
	{
		this.element = element;
	}

	public Agent getAgent()
	{
		return element;
	}


}
