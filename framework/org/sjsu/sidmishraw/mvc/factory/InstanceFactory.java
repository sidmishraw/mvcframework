/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.factory
 * File: InstanceFactory.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 21, 2017 1:57:12 PM
 */
package org.sjsu.sidmishraw.mvc.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * This factory creates singleton objects for the following classes:
 * </br>
 * </br>
 * <strong>CommandProcessor<strong></br>
 * </br>
 * <strong>MVCApp<strong></br>
 * </br>
 * <strong>FileMenuService<strong></br>
 * 
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.factory.InstanceFactory
 *
 */
public class InstanceFactory {
	
	
	private static Map<String, Object> singletonObjectMapping = new HashMap<>();
	
	/**
	 * Made the constructor of the InstaceFactory private to prevent unwanted
	 * instantiation
	 */
	private InstanceFactory() {}
	
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> classz) {
		
		if (null == singletonObjectMapping.get(classz.getName())) {
			
			try {
				
				singletonObjectMapping.put(classz.getName(), classz.newInstance());
			} catch (InstantiationException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			}
		}
		
		return (T) singletonObjectMapping.get(classz.getName());
	}
}
