/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.core
 * File: View.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 19, 2017 9:27:11 PM
 */
package org.sjsu.sidmishraw.mvc.core;

import java.util.Observer;

import javax.swing.JPanel;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.core.View
 *
 */
public abstract class View extends JPanel implements Observer {
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private String				name;
	
	private Model				model;
	
	/**
	 * @param model
	 */
	public View(Model model, String name) {
		
		this.model = model;
		this.name = name;
		
		// register the view as an observer of the Model
		this.model.addObserver(this);
	}
	
	/**
	 * @return the model
	 */
	public Model getModel() {
		
		return this.model;
	}
	
	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Model model) {
		
		this.model = model;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		
		return this.name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		
		this.name = name;
	}
}
