/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.brickcad.core
 * File: SideView.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 3:40:48 PM
 */
package org.sjsu.sidmishraw.brickcad.views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import org.sjsu.sidmishraw.brickcad.core.Brick;
import org.sjsu.sidmishraw.mvc.core.Model;
import org.sjsu.sidmishraw.mvc.core.View;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.brickcad.core.SideView
 *
 */
public class SideView extends View {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param model
	 */
	public SideView(Model model, String name) {
		
		super(model, name);
	}
	
	/**
	 * Paints the brick onto the view
	 */
	@Override
	public void paintComponent(Graphics gc) {
		
		Brick brick = (Brick) this.getModel();
		
		gc.setColor(Color.RED);
		gc.fillRect(100, 100, brick.getWidth().intValue(), brick.getHeight().intValue());
		gc.setColor(Color.RED);
		gc.drawRect(100, 100, brick.getWidth().intValue(), brick.getHeight().intValue());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		repaint();
	}
	
}
