package ru.mipt.agents.behaviour;

import ru.mipt.agents.AgentInterface;

public abstract class AbstractBehaviour implements BehaviourInterface
{
	protected AgentInterface agent;
	protected Integer timeout;

	public AbstractBehaviour(AgentInterface agent)
	{
		this.agent = agent;
	}
	public AbstractBehaviour(AgentInterface agent, Integer timeout)
	{
		this.agent = agent;
		this.timeout = timeout;
	}
}
