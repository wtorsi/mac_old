package ru.mipt.agents;

import ru.mipt.agents.behaviour.BehaviourInterface;
import ru.mipt.agents.model.Signal;
import ru.mipt.container.Container;
import ru.mipt.container.ContainerAwareInterface;
import ru.mipt.controller.CommunicationController;
import ru.mipt.fabric.AgentsContainer;
import ru.mipt.system.Point;
import ru.mipt.system.Settings;

import java.util.ArrayList;
import java.util.UUID;

public abstract class AbstractAgent implements AgentInterface
{

	protected CommunicationController communicationController;

	protected String id = UUID.randomUUID().toString();

	protected Point position = Point.ZERO;

	protected Double size;

	protected Double energy;

	protected Container container;

	protected ArrayList<BehaviourInterface> behaviourList = new ArrayList<BehaviourInterface>();

	public AbstractAgent(Double energy, Double size, Double visibleRadius, Double audibleRadius)
	{
		this.size = size;
		this.energy = energy;

		communicationController = new CommunicationController(this, visibleRadius, audibleRadius);
	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public Double getSize()
	{
		return size;
	}

	public void setSize(Double size)
	{
		this.size = size;
	}

	@Override
	public Point getPosition()
	{
		return position;
	}

	public void setPosition(Point position)
	{
		this.position = position;
	}

	@Override
	public Double getEnergy()
	{
		return energy;
	}

	public void setEnergy(Double energy)
	{
		this.energy = energy;
	}

	@Override
	public void tearDown()
	{

	}

	@Override
	public void setup()
	{

	}

	@Override
	public void observeVideoSignals()
	{
		communicationController.observeVideoSignals();
	}

	@Override
	public Signal getVideoSignal()
	{
		return communicationController.getVideoTransmitter().getSignal();
	}

	@Override
	public void observeAudioSignals()
	{
		communicationController.observeAudioSignals();
	}

	@Override
	public Signal getAudioSignal()
	{
		return communicationController.getAudioTransmitter().getSignal();
	}


	public boolean isAlive()
	{
		return size > Settings.Agent.MIN_ENERGY;
	}

	@Override
	public ArrayList<BehaviourInterface> getBehaviourList()
	{
		return behaviourList;
	}

	@Override
	public ContainerAwareInterface setContainer(Container container)
	{
		this.container = container;
		return this;
	}


	public AgentsContainer getAgentsContainer()
	{
		return (AgentsContainer) container.get("container.agents");
	}


}


