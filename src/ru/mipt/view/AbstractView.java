package ru.mipt.view;

import javafx.scene.Group;
import ru.mipt.agents.AgentInterface;

public abstract class AbstractView extends Group implements ViewInterface
{
	protected AgentInterface agent;

	public AbstractView(AgentInterface agent)
	{
		this.agent = agent;
	}

	abstract public void setup();

	abstract public void update();

	@Override
	public void tearDown()
	{

	}


	@Override
	public boolean isExist()
	{
		return agent != null;
	}
}
