package net.towerquest.towerquest.entity.components;

import java.lang.RuntimeException;

import net.towerquest.towerquest.entity.Component;

public class MissingDependenciesException extends RuntimeException {

	public MissingDependenciesException() {
		super();
	}
	public MissingDependenciesException(String name) {
		super(name);
	}
	public MissingDependenciesException(Class<? extends Component> clazz) {
		this(clazz.getName());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -649801142119452743L;

}
