package ru.mipt.transmitter;


import ru.mipt.agents.AbstractAgent;
import ru.mipt.agents.model.Signal;
import ru.mipt.controller.AbstractController;

abstract public class AbstractTransmitter
{

	protected Signal signal;

	protected AbstractController controller;

	public AbstractTransmitter(AbstractController controller)
	{
		this.controller = controller;

		AbstractAgent agent = controller.getAgent();
		signal = new Signal(agent.getId(), agent.getPosition());

	}

	public Signal getSignal()
	{
		return signal;
	}
}
