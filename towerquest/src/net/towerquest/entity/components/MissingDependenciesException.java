package net.towerquest.entity.components;

import java.lang.RuntimeException;

public class MissingDependenciesException extends RuntimeException {

	public MissingDependenciesException() {
		super();
	}
	public MissingDependenciesException(String name) {
		super(name);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -649801142119452743L;

}
