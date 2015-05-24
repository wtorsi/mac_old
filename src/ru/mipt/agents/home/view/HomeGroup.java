package ru.mipt.agents.home.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import ru.mipt.agents.AbstractAgent;
import ru.mipt.agents.home.Home;
import ru.mipt.view.AbstractView;

public class HomeGroup extends AbstractView
{
	private Circle body;

	private Circle videoReceiver;

	private Circle audioReceiver;

	public HomeGroup(AbstractAgent element)
	{
		super(element);
	}



	@Override
	public Home getElement()
	{
		return (Home) super.getElement();
	}

	public void initialize()
	{
		double size = getElement().getSize();

		body = new Circle(size, Color.web("#505050"));
		getChildren().add(body);


		videoReceiver = new Circle(getElement().getCommunicationController().getAudioReceiver()
				.getReceiveRadius(),  Color.web("red", 0));
		videoReceiver.setStrokeType(StrokeType.OUTSIDE);
		videoReceiver.setStroke(Color.web("#cccccc"));
		videoReceiver.setStrokeWidth(1);

		getChildren().add(videoReceiver);

		audioReceiver = new Circle(getElement().getCommunicationController().getAudioReceiver().getReceiveRadius(), Color.web("red", 0));
		audioReceiver.setStrokeType(StrokeType.OUTSIDE);
		audioReceiver.setStroke(Color.web("#cccccc"));
		audioReceiver.setStrokeWidth(1);

		getChildren().add(audioReceiver);

		getElement().getElementsLayout().getChildren().add(this);

	}

	public void update()
	{
		setTranslateX(getElement().getPosition().getX());
		setTranslateY(getElement().getPosition().getY());
	}
}
