package ru.mipt.agents.station;

import ru.mipt.agents.AbstractAgent;
import ru.mipt.agents.AliveElementInterface;
import ru.mipt.receiver.VideoReceiverAgentInterface;
import ru.mipt.transmitter.VideoTransmitterAgentInterface;
import ru.mipt.agents.station.controller.CommunicationController;
import ru.mipt.agents.station.controller.StorageController;
import ru.mipt.agents.station.view.EnergyStorageGroup;
import ru.mipt.agents.model.Signal;
import ru.mipt.system.Point;
import ru.mipt.system.Random;
import ru.mipt.system.Settings;

public class Station extends AbstractAgent
	implements VideoReceiverAgentInterface, VideoTransmitterAgentInterface, AliveElementInterface
{

	private EnergyStorageGroup energyStorageGroup = new EnergyStorageGroup(this);

	private CommunicationController communicationController = new CommunicationController(this);

	private StorageController storageController = new StorageController(this);

	@Override
	public void create()
	{
		size = new Random(Settings.EnergyStorage.MAX_SIZE, Settings.EnergyStorage.MIN_SIZE).aDouble();
		position = new Point(new Random(Settings.WORLD_WIDTH).aDouble(), new Random(Settings.WORLD_HEIGHT).aDouble());


		communicationController.initialize();
		storageController.initialize();
	}

	@Override
	public void update()
	{
		storageController.expand();
	}

	@Override
	public void postUpdate()
	{

	}

	@Override
	public void preUpdate()
	{

	}

	@Override
	public void destruct()
	{

	}



	@Override
	public void draw()
	{
		energyStorageGroup.update();
	}

	@Override
	public void createView()
	{
		energyStorageGroup.initialize();
	}

	@Override
	public boolean isAlive()
	{
		return false;
	}

	@Override
	public void handleReceivingVideoSignals()
	{
		communicationController.handleReceivingVideoSignals();
	}

	@Override
	public Signal getTransmittedVideoSignal()
	{
		return communicationController.getVideoTransmitter().getSignal();
	}
}
