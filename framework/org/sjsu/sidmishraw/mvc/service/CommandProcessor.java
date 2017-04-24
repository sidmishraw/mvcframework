/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.service
 * File: CommandProcessor.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 1:20:23 PM
 */
package org.sjsu.sidmishraw.mvc.service;

import java.util.Stack;

import org.sjsu.sidmishraw.mvc.core.Command;
import org.sjsu.sidmishraw.mvc.core.Memento;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.service.CommandProcessor
 *
 */
public class CommandProcessor {
	
	// the memento has the last state
	
	// undo stack
	private Stack<Command>	undoStack;
	
	// redo stack
	private Stack<Command>	redoStack;
	
	/**
	 * initializes the undoStack and redoStacks for the application
	 */
	public CommandProcessor() {
		
		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
	}
	
	/**
	 * Execute the command and put the memento onto the undostack if the command
	 * is undoable, first empty the redo stack since the path is changing
	 * 
	 * @param command
	 */
	public void execute(Command command) {
		
		if (null != command) {
			
			// pop all the commands on the redo stack so that new commands can
			// be
			// redone
			if (!this.redoStack.empty()) {
				
				this.redoStack.removeAllElements();
			}
			
			System.out.println("HARMLESS LOGGING:: Before exec: Undo stack size = " + this.undoStack.size()
					+ "  Redo stack size = " + this.redoStack.size());
			
			boolean status = command.execute();
			
			// if command is undoable, push it onto the undo stack and execute
			// it
			if (status && command.getUndoable()) {
				
				System.out.println("Pushing command " + command.getModel().toString() + " onto the undo stack");
				
				// push the command, the memento inside the command has the old
				// state that can be restored
				this.undoStack.push(command);
			}
			
			System.out.println("HARMLESS LOGGING:: After exec: Undo stack size = " + this.undoStack.size()
					+ "  Redo stack size = " + this.redoStack.size());
		}
		
		System.out.println("HARMLESS: CommandProcessor: Undostack size = " + this.undoStack.size()
				+ " Redostack size = " + this.redoStack.size());
	}
	
	/**
	 * Pop from the undostack and push that into the redoStack and then execute
	 * the command
	 */
	public void undo() {
		
		if (!this.undoStack.empty()) {
			
			// push the current state into redo stack
			Command prevCommand = this.undoStack.pop();
			
			prevCommand.setFromUndoRedoStack(true);
			
			// take a snapshot of the current state before altering it
			Memento currentMemento = prevCommand.getModel().makeMemento();
			
			// restored the state of the model from the previous momento
			prevCommand.getModel().accept(prevCommand.getMemento());
			
			System.out.println(
					"HARMLESS LOGGING:: Undo: Executing command with model = " + prevCommand.getModel().toString());
			// make the newMemento from the existing model
			// before accepting changes
			prevCommand.execute();
			
			// set a snapshot of the current state before pushing it onto redo
			// stack
			prevCommand.setMemento(currentMemento);
			
			this.redoStack.push(prevCommand);
			
			System.out.println(
					"HARMLESS LOGGING:: Undo: Executed command with model = " + prevCommand.getModel().toString());
			
		}
		
		System.out.println("HARMLESS: CommandProcessor: Undostack size = " + this.undoStack.size()
				+ " Redostack size = " + this.redoStack.size());
	}
	
	/**
	 * Pop from the redo stack and push that onto the undostack and then execute
	 * the command
	 */
	public void redo() {
		
		if (!this.redoStack.empty()) {
			
			Command nextCommand = this.redoStack.pop();
			
			// take a snapshot of the current state before altering it
			Memento currentMemento = nextCommand.getModel().makeMemento();
			
			nextCommand.setFromUndoRedoStack(true);
			
			System.out.println(
					"HARMLESS LOGGING:: Redo: Executing command with model = " + nextCommand.getModel().toString());
			
			nextCommand.getModel().accept(nextCommand.getMemento());
			
			nextCommand.execute();
			
			// set a snapshot of the current state before pushing it onto redo
			// stack
			nextCommand.setMemento(currentMemento);
			
			// save the nextCommand in undostack before modifying and executing
			// the state
			this.undoStack.push(nextCommand);
			
			System.out.println(
					"HARMLESS LOGGING:: Redo: Executed command with model = " + nextCommand.getModel().toString());
			
		}
		
		System.out.println("HARMLESS: CommandProcessor: Undostack size = " + this.undoStack.size()
				+ " Redostack size = " + this.redoStack.size());
	}
	
	/**
	 * used to empty the undo and redo stacks
	 */
	public void emptyUndoRedoStack() {
		
		this.undoStack.removeAllElements();
		this.redoStack.removeAllElements();
	}
}
