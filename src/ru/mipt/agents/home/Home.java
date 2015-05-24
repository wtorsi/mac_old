package ru.mipt.agents.home;

import ru.mipt.receiver.AudioReceiverAgentInterface;
import ru.mipt.receiver.VideoReceiverAgentInterface;
import ru.mipt.system.Point;
import ru.mipt.agents.*;
import ru.mipt.agents.home.controller.CommunicationController;
import ru.mipt.agents.home.controller.DecisionController;
import ru.mipt.agents.home.controller.EnergyController;
import ru.mipt.agents.home.view.HomeGroup;
import ru.mipt.agents.model.Signal;
import ru.mipt.system.Random;
import ru.mipt.system.Settings;
import ru.mipt.transmitter.AudioTransmitterAgentInterface;
import ru.mipt.transmitter.VideoTransmitterAgentInterface;

public class Home extends AbstractAgent
		implements

		VideoTransmitterAgentInterface, VideoReceiverAgentInterface, AudioTransmitterAgentInterface,
		AudioReceiverAgentInterface, IntelligentElementInterface, AliveElementInterface
{

	private HomeGroup sceneGroup = new HomeGroup(this);

	private CommunicationController communicationController = new CommunicationController(this);


	private DecisionController decisionController = new DecisionController(this);

	private EnergyController energyController = new EnergyController(this);


	@Override
	public void create()
	{
		size = new Random(Settings.Home.MAX_START_SIZE, Settings.Home.MIN_START_SIZE).aDouble();

		position = new Point( (double)  Settings.WORLD_WIDTH / 2, (double) Settings.WORLD_HEIGHT / 2);

		energyController.initialize();
		communicationController.initialize();
		decisionController.initialize();
	}

	@Override
	public void update()
	{

	}

	@Override
	public void destruct()
	{
		container.getElementsLayout().getChildren().remove(sceneGroup);
	}


	@Override
	public void handleReceivingAudioSignals()
	{
		communicationController.handleReceivingAudioSignals();
	}



	@Override
	public Signal getTransmittedAudioSignal()
	{
		return communicationController.getAudioTransmitter().getSignal();
	}

	@Override
	public void makeDecisions()
	{

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

	@Override
	public void postUpdate()
	{

	}

	@Override
	public void preUpdate()
	{

	}

	@Override
	public boolean isAlive()
	{
		return energyController.isAlive();
	}

	@Override
	public void draw()
	{
		sceneGroup.update();
	}

	@Override
	public void createView()
	{
		sceneGroup.initialize();

	}

//	getter and setters


	public CommunicationController getCommunicationController()
	{
		return communicationController;
	}

	public DecisionController getDecisionController()
	{
		return decisionController;
	}

	public EnergyController getEnergyController()
	{
		return energyController;
	}
}
