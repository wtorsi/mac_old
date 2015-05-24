package ru.mipt.agents.agent.manager;

import ru.mipt.agents.agent.Agent;
import ru.mipt.agents.model.Signal;
import ru.mipt.agents.home.Home;

import java.util.ArrayList;

public class HomeManager extends AbstractManager
{
	private ru.mipt.agents.agent.model.Home home;
	
	public HomeManager(Agent element)
	{
		super(element);
	}

	public boolean hasHome()
	{
		return home != null;
	}
	
	
	public ru.mipt.agents.agent.model.Home getHome()
	{
		return home;
	}
	
	public ru.mipt.agents.agent.model.Home findHome()
	{
		ArrayList<Signal> signals = element.getCommunicationController().getUniqueSignals(Home.class.getName());
		
		if (signals.size() > 0) {
			
			ru.mipt.agents.agent.model.Home possibleHome = new ru.mipt.agents.agent.model.Home();
			Double minDistance = Double.MAX_VALUE;
			
			for (Signal signal : signals) {
				
				Double distanceToHome = signal.getPosition().subtract(element.getPosition()).magnitude();
				
				if (distanceToHome < minDistance) {
					minDistance = distanceToHome;
					possibleHome
							.setId(signal.getId())
							.setSize(signal.getSize())
							.setPosition(signal.getPosition())
							.setReceiverRadius(signal.getHome().getReceiverRadius());
					
				}
			}
			
			return possibleHome;
		}
		
		return null;
	}
	
	public void setHome(ru.mipt.agents.agent.model.Home home)
	{
		this.home = home;
	}
}
