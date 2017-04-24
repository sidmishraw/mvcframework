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
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
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
	private static final long serialVersionUID = 1L;
	
	/**
	 * The inner memento used to hide complexity from user of the framework
	 * 
	 * @author sidmishraw
	 *
	 *         Qualified Name: org.sjsu.sidmishraw.mvc.core.IMemento
	 *
	 */
	private final class IMemento implements Memento {
		
		
		private HashMap<String, Object> fields = new HashMap<>();
		
		public Object getValue(String fieldName) {
			
			Object value = null;
			
			value = this.fields.get(fieldName);
			
			return value;
		}
		
		// not used locally but use via reflection while updating values
		// of the memento
		@SuppressWarnings("unused")
		public void setValue(String fieldName, Object value) {
			
			this.fields.put(fieldName, value);
		}
	}
	
	// model fields
	private String	fileName;
	private Boolean	unsavedChanges	= false;
	
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
	
	/**
	 * This is used to notify the views listening on the Model for changes
	 */
	public void changed() {
		
		this.unsavedChanges = true;
		
		// setChanged sets the flag for change in the Observable super class
		this.setChanged();
		
		// notify all the subscribers
		this.notifyObservers();
		
		// We call this to say that we have notified all the
		// subsribers/observers
		// Indicates that this object has no longer changed, or that it has
		// already notified all of its observers of its most recent change, so
		// that the hasChanged method will now return false. This method is
		// called automatically by the notifyObservers methods.
		this.clearChanged();
	}
	
	/**
	 * Makes a new Memento for the model that is used for undo-redo operations
	 * 
	 * @return {@link Memento}
	 */
	public final Memento makeMemento() {
		
		Field[] fields = this.getClass().getDeclaredFields();
		
		IMemento memento = new IMemento();
		
		Arrays.asList(fields).forEach(field -> {
			
			if (!field.getName().contains("serialVersionUID")) {
				
				try {
					
					// set the fields to be accessible
					field.setAccessible(true);
					memento.getClass().getMethod("setValue", String.class, Object.class).invoke(memento,
							field.getName(), field.get(this));
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
		
		return memento;
	}
	
	/**
	 * Restores the state of the Model from the Memento
	 * 
	 * @param memento
	 */
	public final void accept(Memento memento) {
		
		IMemento imemento = (IMemento) memento;
		
		// update all the fields of the model from the information from the
		// imemento
		Arrays.asList(this.getClass().getDeclaredFields()).stream().forEach(field -> {
			
			String fieldName = field.getName();
			
			if (!fieldName.contains("serialVersionUID")) {
				
				Object value = imemento.getValue(fieldName);
				
				try {
					
					// set the field to be accessible and then set the value
					// into it
					field.setAccessible(true);
					field.set(this, value);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}
	
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
