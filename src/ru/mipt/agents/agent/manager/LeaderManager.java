package ru.mipt.agents.agent.manager;

import ru.mipt.agents.agent.Agent;
import ru.mipt.agents.agent.model.Leader;
import ru.mipt.agents.model.Signal;

import java.util.ArrayList;

public class LeaderManager extends AbstractManager
{
	private Leader leader;

	public LeaderManager(Agent agent)
	{
		super(agent);
	}

	public boolean hasLeader()
	{
		return leader != null;
	}

	public Leader selectLeader()
	{
		ArrayList<Signal> signals = element.getCommunicationController().getUniqueSignals(Agent.class
				.getName());
		Integer maxResolvedAgentsSize = Integer.MIN_VALUE;
		Leader possibleLeader = new Leader();

		for (Signal signal : element.getCommunicationController().getUniqueSignals()) {

			Integer resolvedAgentsSize = signal.getAgent().getResolvedAgentsSize();

			if (resolvedAgentsSize > maxResolvedAgentsSize) {
				maxResolvedAgentsSize = resolvedAgentsSize;

				possibleLeader.setId(signal.getId())
						.setEnergy(signal.getEnergy())
						.setPosition(signal.getPosition())
						.setResolvedAgentsSize(signal.getAgent().getResolvedAgentsSize());

			}
		}

		return (possibleLeader.getResolvedAgentsSize() > signals.size() ? possibleLeader : null);
	}

	public void setLeader(Leader leader)
	{
		this.leader = leader;
	}
}
