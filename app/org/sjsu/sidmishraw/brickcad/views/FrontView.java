/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.brickcad.core
 * File: FrontView.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 3:41:17 PM
 */
package org.sjsu.sidmishraw.brickcad.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;

import javax.swing.SwingUtilities;

import org.sjsu.sidmishraw.brickcad.core.Brick;
import org.sjsu.sidmishraw.mvc.core.Model;
import org.sjsu.sidmishraw.mvc.core.View;
import org.sjsu.sidmishraw.mvc.core.ViewFrame;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.brickcad.core.FrontView
 *
 */
public class FrontView extends View {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param model
	 */
	public FrontView(Model model, String name) {
		
		super(model, name);
	}
	
	/**
	 * Paints the brick onto the view
	 */
	@Override
	public void paintComponent(Graphics gc) {
		
		Brick brick = (Brick) this.getModel();
		
		gc.setColor(Color.RED);
		gc.fillRect(100, 100, brick.getLength().intValue(), brick.getHeight().intValue());
		gc.setColor(Color.RED);
		gc.drawRect(100, 100, brick.getLength().intValue(), brick.getHeight().intValue());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable subject, Object message) {
		
		// repaint the view after updates
		this.repaint();
		
		this.setPreferredSize(new Dimension(((Brick) this.getModel()).getLength().intValue() + 350,
				((Brick) this.getModel()).getHeight().intValue() + 250));
		
		ViewFrame myparent = ((ViewFrame) SwingUtilities.getAncestorOfClass(ViewFrame.class, this));
		
		myparent.setPreferredSize(this.getPreferredSize());
		
		myparent.pack();
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(((Brick) this.getModel()).getLength().intValue() + 350,
				((Brick) this.getModel()).getHeight().intValue() + 250);
	}
}
