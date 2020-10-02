package presentacion;

import business_logic.ControllersFactory;

public abstract class ViewsFactory {

	private static ViewsFactory concreteFactory;

	protected ControllersFactory controllers;
		
	public void setControllers(ControllersFactory controllers) {
		this.controllers = controllers;
	}
	
	public static void setFactory(ViewsFactory factory) {
		assert factory != null;
		concreteFactory = factory;
	}
	
	public static ViewsFactory getFactory() {
		return concreteFactory;
	}
	
	public abstract Presenter makePresenter();
}