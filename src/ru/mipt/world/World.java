package ru.mipt.world;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.mipt.agents.AgentInterface;
import ru.mipt.agents.agent.Agent;
import ru.mipt.agents.agent.view.View;
import ru.mipt.agents.behaviour.BehaviourInterface;
import ru.mipt.agents.station.Station;
import ru.mipt.container.Container;
import ru.mipt.fabric.AgentsContainer;
import ru.mipt.fabric.ViewsContainer;
import ru.mipt.receiver.AudioReceiverAgentInterface;
import ru.mipt.receiver.VideoReceiverAgentInterface;
import ru.mipt.system.Point;
import ru.mipt.system.Random;
import ru.mipt.system.Range;
import ru.mipt.system.Settings;
import ru.mipt.view.ViewInterface;

import java.util.Iterator;
import java.util.Map;

public class World extends AbstractWorld
{


	public World(int fps, String title)
	{
		super(fps, title);
	}

	@Override
	public void initialize(Stage primaryStage)
	{
		primaryStage.setTitle(getWindowTitle());

		// Create the scene
		setLayout(new Group());
		setWorldSurface(new Scene(getLayout(), Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT));
		primaryStage.setScene(getWorldSurface());

		initContainer();

//		initHome();
		initAgents(Settings.START_AGENTS_SIZE);
//		initStations(Settings.START_STATIONS_SIZE);
	}


	private void initContainer()
	{
		container = new Container();
		container.put("elements.layout", getLayout());
		container.put("container.agents", (new AgentsContainer()).setContainer(container));
		container.put("container.vies", (new ViewsContainer()).setContainer(container));
	}


	private void initHome()
	{
//		getElementFabric().addElement(new Home(), new HomeView());
	}

	private void initAgents(int num)
	{
		AgentsContainer agentsContainer = getAgentsContainer();
		ViewsContainer viewsContainer = getViewsContainer();

		for (int i : new Range(num)) {

			Double size = new Random(Settings.Agent.MAX_START_SIZE, Settings.Agent.MIN_START_SIZE).aDouble(),
					energy = new Random(Settings.Agent.MAX_START_ENERGY, Settings.Agent.MIN_START_ENERGY).aDouble(),
					visibleRadius = size * Settings.Agent.VISIBLE_RADIUS_MULTIPLIER,
					audibleRadius = size * Settings.Agent.AUDIBLE_RADIUS_MULTIPLIER;

			Agent agent = new Agent(energy, size, visibleRadius, audibleRadius);
			agent.setPosition(new Point((double) (Settings.WORLD_WIDTH / 2), (double) (Settings.WORLD_HEIGHT / 2)));

			agent = (Agent) agentsContainer.addAgent(agent);
			viewsContainer.addView(new View(agent));
		}
	}

	private void initStations(int num)
	{

		AgentsContainer agentsContainer = getAgentsContainer();

		for (int i : new Range(num)) {
			agentsContainer.addAgent(new Station());
		}
	}


	@Override
	protected void preUpdateAgents()
	{

		AgentsContainer agentsContainer = getAgentsContainer();

		for (Map.Entry<String, AgentInterface> entry : agentsContainer.getAgents().entrySet()) {

			AgentInterface element = entry.getValue();

			if (element.isAlive()) {
//				todo: will cause exception
				agentsContainer.removeAgent(element);
				return;
			}

			if (element instanceof AudioReceiverAgentInterface) {
				((AudioReceiverAgentInterface) element).observeAudioSignals();
			}

			if (element instanceof VideoReceiverAgentInterface) {
				((VideoReceiverAgentInterface) element).observeVideoSignals();
			}

		}
	}


	@Override
	protected void updateAgents()
	{

		AgentsContainer agentsContainer = getAgentsContainer();

		for (Map.Entry<String, AgentInterface> entry : agentsContainer.getAgents().entrySet()) {

			AgentInterface element = entry.getValue();

			for (BehaviourInterface behavior : element.getBehaviourList()) {
				if (behavior.isActive()) {
					behavior.process();
				}
			}

		}
	}


	@Override
	protected void updateViews()
	{
		ViewsContainer viewsContainer = getViewsContainer();

		Iterator<ViewInterface> iterator = viewsContainer.getViews().iterator();
		while (iterator.hasNext()) {

			ViewInterface view = iterator.next();

			if (!view.isExist()) {
				iterator.remove();
				continue;
			}
			view.update();
		}
	}

}
