package ru.mipt.fabric;

import ru.mipt.agents.AgentInterface;
import ru.mipt.container.ContainerAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AgentsContainer extends ContainerAware
{

	private Map<String, ArrayList<AgentInterface>> agents = new HashMap<String, ArrayList<AgentInterface>>();


	public AgentInterface addAgent(AgentInterface agent)
	{
		agent.setContainer(container);
		agent.setup();
		putAgent(agent);

		return agent;
	}


	private void putAgent(AgentInterface agent)
	{

		String className = agent.getClass().getSimpleName();

		ArrayList<AgentInterface> list = agents.get(className);

		if (list == null) {
			list = new ArrayList<AgentInterface>();
		}

		list.add(agent);
		agents.put(className, list);
	}


	public void removeAgent(AgentInterface agent)
	{
		agent.tearDown();
		deleteAgent(agent);
	}

	
	private void deleteAgent(AgentInterface agent)
	{
		
		String className = agent.getClass().getName();
		
		ArrayList<AgentInterface> list = agents.get(className);
		if (list == null) {
			list = new ArrayList<AgentInterface>();
		}
		list.remove(agent);
		agents.put(className, list);

	}


	public Map<String, AgentInterface> getAgents()
	{

		Map<String, AgentInterface> list = new HashMap<String, AgentInterface>();

		for (Map.Entry<String, ArrayList<AgentInterface>> entry : agents.entrySet()) {
			if (entry.getValue() != null) {
				for (AgentInterface agent : entry.getValue()) {
					if (!list.containsKey(agent.getId())) {
						list.put(agent.getId(), agent);
					}
				}
			}
		}

		return list;
	}

}
