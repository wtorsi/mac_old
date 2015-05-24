package ru.mipt.container;


import javafx.scene.Group;
import ru.mipt.fabric.AgentsContainer;
import ru.mipt.fabric.ViewsContainer;

public class ContainerAware implements ContainerAwareInterface
{
	protected Container container;

	public ContainerAware setContainer(Container container)
	{
		this.container = container;

		return this;
	}


	public Group getElementsLayout()
	{
		return (Group) container.get("elements.layout");
	}

	public AgentsContainer getAgentsContainer()
	{
		return (AgentsContainer) container.get("container.agents");
	}

	public ViewsContainer getViewsContainer()
	{
		return (ViewsContainer) container.get("container.views");
	}

}
