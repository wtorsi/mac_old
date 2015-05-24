package ru.mipt.container;

import javafx.scene.Group;

import java.util.HashMap;
import java.util.Map;

public class Container
{
	protected Map<String, Object> container = new HashMap<String, Object>();


	public void put(String key, Object value)
	{
		container.put(key, value);
	}

	public Object get(String key)
	{
		return container.get(key);
	}

	public Group getElementsLayout()
	{
		return (Group) container.get("elements.layout");
	}

}
