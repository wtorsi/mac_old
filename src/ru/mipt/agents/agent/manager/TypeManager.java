package ru.mipt.agents.agent.manager;

import ru.mipt.agents.agent.Agent;
import ru.mipt.agents.agent.type.Type;
import ru.mipt.agents.model.Signal;
import ru.mipt.system.Random;
import ru.mipt.system.Settings;

import java.util.ArrayList;

public class TypeManager extends AbstractManager
{
	private Type type;

	public TypeManager(Agent agent)
	{
		super(agent);
	}

	public Type selectType()
	{
		ArrayList<Signal> signals = element.getCommunicationController().getUniqueSignals(Agent.class.getName());

		Double minSize = Double.MAX_VALUE;
		Double maxSize = Double.MIN_VALUE;

		for (Signal signal : signals) {

			Double size = signal.getSize();
			if (size <= minSize) {
				minSize = size;
			}

			if (size >= maxSize) {
				maxSize = size;
			}
		}

		Double thresholdValue = new Random(Settings.Agent.DecisionController.MAX_SIZE_THRESHOLD,
				Settings.Agent.DecisionController.MAX_SIZE_THRESHOLD).aDouble();
		Double threshold = minSize + thresholdValue * (maxSize - minSize);

		return element.getSize() > threshold ? Type.DISCOVERER : Type.DEFENDER;
	}


	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

}
