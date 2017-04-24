/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.core
 * File: ExecuteLogic.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 23, 2017 1:26:38 PM
 */
package org.sjsu.sidmishraw.mvc.core;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.core.ExecuteLogic
 *
 */
@FunctionalInterface
public interface ExecuteLogic {
	
	
	/**
	 * 
	 * @return boolean -- status of the execution
	 */
	public boolean executeLogic();
}
