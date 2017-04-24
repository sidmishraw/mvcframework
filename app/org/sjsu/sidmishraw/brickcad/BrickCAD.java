/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.brickcad
 * File: BrickCAD.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 7:12:28 PM
 */
package org.sjsu.sidmishraw.brickcad;

import org.sjsu.sidmishraw.brickcad.factory.BrickFactory;
import org.sjsu.sidmishraw.mvc.factory.InstanceFactory;
import org.sjsu.sidmishraw.mvc.service.MVCApp;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.brickcad.BrickCAD
 *
 */
public class BrickCAD {
	
	
	/**
	 * 
	 */
	public BrickCAD() {}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MVCApp.run(InstanceFactory.getInstance(BrickFactory.class));
	}
	
}
