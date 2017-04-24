/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.factory
 * File: AppFactory.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 1:23:40 PM
 */
package org.sjsu.sidmishraw.mvc.factory;

import java.util.List;

import org.sjsu.sidmishraw.mvc.core.Command;
import org.sjsu.sidmishraw.mvc.core.Model;
import org.sjsu.sidmishraw.mvc.core.View;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.factory.AppFactory
 *
 */
public interface AppFactory {
	
	
	/**
	 * Makes a new model instance with default values
	 * 
	 * @return Model
	 */
	public Model makeModel();
	
	/**
	 * Makes a new view instance for the given view type
	 * 
	 * @param viewType
	 * @return View
	 */
	public View makeView(Model model, String viewType);
	
	/**
	 * Makes a new command instance for the given command type
	 * 
	 * @param commandType
	 * @return Command
	 */
	public Command makeCommand(String commandType);
	
	/**
	 * The file menus
	 * 
	 * @return {@link List}<String>
	 */
	public List<String> getFileMenus();
	
	/**
	 * Returns the list of all the views defined in the application
	 * 
	 * @return {@link List}<String>
	 */
	public List<String> getViews();
	
	/**
	 * Returns the list of all the commands defined in the application
	 * 
	 * @return {@link List}<String>
	 */
	public List<String> getCommands();
	
	/**
	 * Returns the title of the application
	 * 
	 * @return {@link String}
	 */
	public String getTitle();
	
	/**
	 * Returns the help string defined for the application
	 * 
	 * @return {@link String}
	 */
	public String getHelp();
	
	/**
	 * Returns the about string defined for the application
	 * 
	 * @return {@link String}
	 */
	public String about();
}
