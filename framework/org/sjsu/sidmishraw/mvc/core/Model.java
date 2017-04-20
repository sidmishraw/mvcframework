/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.core
 * File: Model.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 19, 2017 9:17:32 PM
 */
package org.sjsu.sidmishraw.mvc.core;

import java.io.Serializable;
import java.util.Observable;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.core.Model
 *
 */
public abstract class Model extends Observable implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private String				fileName;
	private Boolean				unsavedChanges		= false;
	
	/**
	 * 
	 */
	public Model() {}
	
	/**
	 * @param fileName
	 * @param unsavedChanges
	 */
	public Model(String fileName, Boolean unsavedChanges) {
		
		this.fileName = fileName;
		this.unsavedChanges = unsavedChanges;
	}
	
	public void changed() {
		
		this.unsavedChanges = true;
		this.notifyObservers();
	}
	
	public abstract Memento makeMemento();
	
	public abstract void accept(Memento memento);
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return this.fileName;
	}
	
	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * @return the unsavedChanges
	 */
	public Boolean getUnsavedChanges() {
		return this.unsavedChanges;
	}
	
	/**
	 * @param unsavedChanges
	 *            the unsavedChanges to set
	 */
	public void setUnsavedChanges(Boolean unsavedChanges) {
		this.unsavedChanges = unsavedChanges;
	}
}
