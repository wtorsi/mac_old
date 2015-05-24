package ru.mipt.agents.agent.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import ru.mipt.agents.AgentInterface;
import ru.mipt.agents.agent.Agent;
import ru.mipt.agents.agent.model.Home;
import ru.mipt.system.Settings;
import ru.mipt.view.AbstractView;

public class View extends AbstractView
{

	private Circle body;

	private Circle videoReceiver;

	private Circle audioReceiver;

	public View(AgentInterface agent)
	{
		super(agent);
	}

	public void setup()
	{
		double size = agent.getSize();
		Agent agent = (Agent) this.agent;

//		body
		body = new Circle(size);
		getChildren().add(View.NodeIndex.BODY.getValue(), body);

//		video Receiver
		videoReceiver = new Circle(
				agent.getCommunicationController().getVideoReceiver().getReceiveRadius(),
				Color.web("white", 0)
		);

		videoReceiver.setStrokeType(StrokeType.OUTSIDE);
		videoReceiver.setStroke(Color.web("#505050", 0.4));
		videoReceiver.setStrokeWidth(1);
		getChildren().add(NodeIndex.CONTROLLERS.getValue(), videoReceiver);

//      audio receive
		audioReceiver = new Circle(
				agent.getCommunicationController().getAudioReceiver().getReceiveRadius(),
				Color.web("white", 0)
		);

		audioReceiver.setStrokeType(StrokeType.OUTSIDE);
		audioReceiver.setStroke(Color.web("#505050", 0.4));
		audioReceiver.setStrokeWidth(1);
		getChildren().add(NodeIndex.CONTROLLERS.getValue(), audioReceiver);
		
	}


	public void update()
	{

		Agent agent = (Agent) this.agent;

		setTranslateX(agent.getPosition().getX());
		setTranslateY(agent.getPosition().getY());

		body.setRadius(agent.getSize() * agent.getEnergy() / Settings.Agent.MAX_START_ENERGY);
//
//		body.setFill(agent.getDecisionController().getLeaderManager().hasLeader() ? Color.web("red") : Color.web("green"));
//
//		if (agent.getDecisionController().getHomeManager().hasHome()) {
//			Home home = agent.getDecisionController().getHomeManager().getHome();
//			if (agent.getPositionController().isInside(home.getPosition(), home.getReceiverRadius())) {
//
//				videoReceiver.setVisible(false);
//				audioReceiver.setVisible(false);
//			} else {
//				videoReceiver.setVisible(true);
//				audioReceiver.setVisible(true);
//			}
//		}

	}

	public enum NodeIndex
	{
		BODY(0),
		CONTROLLERS(1);

		private final int value;

		private NodeIndex(final int newValue)
		{
			value = newValue;
		}

		public int getValue()
		{
			return value;
		}
	}
}
