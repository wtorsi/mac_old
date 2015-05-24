package ru.mipt.fabric;

import javafx.scene.Node;
import ru.mipt.container.ContainerAware;
import ru.mipt.view.ViewInterface;

import java.util.ArrayList;

public class ViewsContainer extends ContainerAware
{
	private ArrayList<ViewInterface> views = new ArrayList<ViewInterface>();

	public ViewInterface addView(ViewInterface view)
	{

		view.setup();
		putView(view);

		return view;
	}


	public void putView(ViewInterface view)
	{
		views.add(view);
		getElementsLayout().getChildren().add((Node) view);
	}


	private void deleteView(ViewInterface view)
	{

		views.remove(view);
		getElementsLayout().getChildren().remove((Node) view);
	}

	public void removeView(ViewInterface view)
	{
		view.tearDown();
		deleteView(view);
	}


	public ArrayList<ViewInterface> getViews()
	{
		return views;
	}
}
