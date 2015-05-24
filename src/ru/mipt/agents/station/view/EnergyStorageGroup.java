package ru.mipt.agents.station.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ru.mipt.agents.AbstractAgent;
import ru.mipt.agents.station.Station;
import ru.mipt.view.AbstractView;

public class EnergyStorageGroup extends AbstractView
{
	private Circle body;


	public EnergyStorageGroup(AbstractAgent element)
	{
		super(element);
	}

	@Override
	public Station getElement()
	{
		return (Station) super.getElement();
	}

	@Override
	public void initialize()
	{

		Station element = getElement();

		body = new Circle(element.getSize(), Color.web("green"));
		getChildren().add(body);

		getElement().getElementsLayout().getChildren().add(this);
	}

	@Override
	public void update()
	{

	}
}
