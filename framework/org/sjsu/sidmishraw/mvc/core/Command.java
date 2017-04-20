/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.core
 * File: Command.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 19, 2017 9:29:49 PM
 */
package org.sjsu.sidmishraw.mvc.core;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.core.Command
 *
 */
public abstract class Command {
	
	
	private Boolean	undoable;
	private Memento	memento	= null;
	protected Model	model;
	
	/**
	 * 
	 */
	public Command() {}
	
	/**
	 * @param undoable
	 * @param memento
	 * @param model
	 */
	public Command(Boolean undoable, Memento memento, Model model) {
		this.undoable = undoable;
		this.memento = memento;
		this.model = model;
	}
	
	/**
	 * @param undoable
	 */
	public Command(Boolean undoable) {
		
		this.undoable = undoable;
	}
	
	/**
	 * If a command is undoable, then Command.execute() asks the model to create
	 * a memento, which the command saves.
	 * 
	 * This logic should be in some other final method so that the user of the
	 * framework
	 * doesn't modify this?
	 */
	public void execute() {
		
		if (this.undoable) {
			
			this.memento = this.model.makeMemento();
		}
	}
	
	public void undo() {
		
	}
	
	/**
	 * @return the undoable
	 */
	public Boolean getUndoable() {
		return this.undoable;
	}
	
	/**
	 * @param undoable
	 *            the undoable to set
	 */
	public void setUndoable(Boolean undoable) {
		this.undoable = undoable;
	}
	
	/**
	 * @return the memento
	 */
	public Memento getMemento() {
		return this.memento;
	}
	
	/**
	 * @param memento
	 *            the memento to set
	 */
	public void setMemento(Memento memento) {
		this.memento = memento;
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
	
}
