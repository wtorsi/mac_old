package ru.mipt.receiver;


import ru.mipt.controller.AbstractController;
import ru.mipt.agents.model.Signal;

import java.util.ArrayList;

abstract public class AbstractReceiver
{
	protected double receiveRadius;

	protected AbstractController controller;

	public AbstractReceiver(double receiveRadius, AbstractController controller)
	{
		this.receiveRadius = receiveRadius;
		this.controller = controller;
	}


	abstract public ArrayList<Signal> receive();


	public double getReceiveRadius()
	{
		return receiveRadius;
	}
}
