/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.core
 * File: Command.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 19, 2017 9:29:49 PM
 */
package org.sjsu.sidmishraw.mvc.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.core.Command
 *
 */
public class Command {
	
	
	private Boolean				undoable			= true;
	private Boolean				fromUndoRedoStack	= false;
	private Memento				memento				= null;
	
	// logic of the command
	private ExecuteLogic		logic				= null;
	private Model				model;
	private Map<String, Object>	paramMap			= new HashMap<String, Object>();
	
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
	 * @param logic
	 */
	public Command(Boolean undoable, ExecuteLogic logic) {
		
		this.undoable = undoable;
		this.logic = logic;
	}
	
	/**
	 * If a command is undoable, then Command.execute() asks the model to create
	 * a memento, which the command saves.
	 * 
	 * This logic should be in some other final method so that the user of the
	 * framework
	 * doesn't modify this?
	 * 
	 * Planning to use a lambda instead of a functional override, I think it
	 * makes more sense
	 * if the developer is able to pass the code he wants to execute into the
	 * funciton than
	 * override a method
	 */
	public final boolean execute() {
		
		// framework specific logic, not to be overridden by the user
		if (this.undoable) {
			
			this.memento = this.model.makeMemento();
		}
		
		this.logic.executeLogic();
		
		this.model.changed();
		
		return true;
	}
	
	/**
	 * To be implemented by the subclasses of the Command
	 * Holds the business logic for execution of the command specifically
	 */
	// public abstract void executeLogic();
	
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
	
	/**
	 * @return the fromUndoRedoStack
	 */
	public Boolean getFromUndoRedoStack() {
		return this.fromUndoRedoStack;
	}
	
	/**
	 * @param fromUndoRedoStack
	 *            the fromUndoRedoStack to set
	 */
	public void setFromUndoRedoStack(Boolean fromUndoRedoStack) {
		this.fromUndoRedoStack = fromUndoRedoStack;
	}
	
	/**
	 * @return the logic
	 */
	public ExecuteLogic getLogic() {
		return this.logic;
	}
	
	/**
	 * @param logic
	 *            the logic to set
	 */
	public void setLogic(ExecuteLogic logic) {
		this.logic = logic;
	}
	
	/**
	 * @return the paramMap
	 */
	public Map<String, Object> getParamMap() {
		return this.paramMap;
	}
	
	/**
	 * @param paramMap
	 *            the paramMap to set
	 */
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
