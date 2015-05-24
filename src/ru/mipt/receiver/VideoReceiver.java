package ru.mipt.receiver;

import ru.mipt.agents.AbstractAgent;
import ru.mipt.agents.AgentInterface;
import ru.mipt.agents.model.Signal;
import ru.mipt.controller.AbstractController;

import java.util.ArrayList;
import java.util.Map;

public class VideoReceiver extends AbstractReceiver
{

	public VideoReceiver(double receiveRadius, AbstractController controller)
	{
		super(receiveRadius, controller);
	}

	public ArrayList<Signal> receive()
	{
		Map<String, AgentInterface> map = controller.getAgent().getAgentsContainer().getAgents();
		ArrayList<Signal> signals = new ArrayList<Signal>();

		AbstractAgent agent = controller.getAgent();

		for (Map.Entry<String, AgentInterface> entry : map.entrySet()) {

			AgentInterface element = entry.getValue();

			if (element
					.getPosition()
					.subtract(agent.getPosition())
					.magnitude() <= receiveRadius) {

				Signal signal = element.getVideoSignal();
				signals.add(signal);
			}
		}

		return signals;
	}
}
