/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.service
 * File: FileMenuService.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 21, 2017 1:56:14 PM
 */
package org.sjsu.sidmishraw.mvc.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.sjsu.sidmishraw.mvc.core.Model;
import org.sjsu.sidmishraw.mvc.util.Utility;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.service.FileMenuService
 *
 */
public class FileMenuService {
	
	
	/**
	 * 
	 * @param model
	 */
	public static void saveChanges(Model model) {
		
		if (model.getUnsavedChanges() && Utility.confirm("current model has unsaved changes, continue?")) {
			
			save(model);
		}
	}
	
	/**
	 * 
	 * @param model
	 */
	public static void save(Model model) {
		
		String fName = model.getFileName();
		
		if (fName == null) {
			
			fName = Utility.askUser("Enter a file name");
			model.setFileName(fName);
		}
		
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName))) {
			
			os.writeObject(model);
			model.setUnsavedChanges(false);
		} catch (Exception err) {
			
			Utility.error(err.getMessage());
		}
	}
	
	/**
	 * 
	 * @param model
	 */
	public static void saveAs(Model model) {
		
		String fName = model.getFileName();
		
		if (fName == null) {
			
			fName = Utility.askUser("Enter a file name");
			model.setFileName(fName);
		}
		
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName))) {
			
			os.writeObject(model);
			model.setUnsavedChanges(false);
		} catch (Exception err) {
			
			Utility.error(err.getMessage());
		}
	}
	
	/**
	 * 
	 * @param model
	 */
	public static Model open(Model model) {
		
		String fileName = Utility.askUser("Enter a file name");
		model.setFileName(fileName);
		
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))) {
			
			model = (Model) is.readObject();
			model.setUnsavedChanges(false);
			
		} catch (Exception e) {
			
			Utility.error(e.getMessage());
		}
		
		return model;
	}
}
