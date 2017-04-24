/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.core
 * File: ViewFrame.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 6:36:21 PM
 */
package org.sjsu.sidmishraw.mvc.core;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.core.ViewFrame
 *
 */
public class ViewFrame extends JInternalFrame {
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private static int			openFrameCount		= 0;
	
	/**
	 * 
	 */
	public ViewFrame() {}
	
	/**
	 * 
	 * @param view
	 */
	public ViewFrame(View view) {
		
		super(view.getName(), true, true, true, true);
		
		// for separating the opening coordinates
		openFrameCount++;
		
		setTitle(view.getName());
		setContentPane(view);
		
		setPreferredSize(new Dimension(350, 250));
		
		setLocation(30 * openFrameCount, 30 * openFrameCount);
		
		pack();
	}
}
