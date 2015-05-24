package ru.mipt.agents;


import ru.mipt.agents.behaviour.BehaviourInterface;
import ru.mipt.container.ContainerAwareInterface;
import ru.mipt.receiver.AudioReceiverAgentInterface;
import ru.mipt.receiver.VideoReceiverAgentInterface;
import ru.mipt.system.Point;
import ru.mipt.transmitter.AudioTransmitterAgentInterface;
import ru.mipt.transmitter.VideoTransmitterAgentInterface;

import java.util.ArrayList;

public interface AgentInterface
		extends ContainerAwareInterface,
		        AudioReceiverAgentInterface, AudioTransmitterAgentInterface,
		        VideoReceiverAgentInterface, VideoTransmitterAgentInterface
{

	String getId();

	Double getSize();

	Double getEnergy();

	Point getPosition();

	boolean isAlive();

	void setup();

	void tearDown();

	ArrayList<BehaviourInterface> getBehaviourList();
	
}

