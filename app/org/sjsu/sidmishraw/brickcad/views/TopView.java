/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.brickcad.core
 * File: TopView.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 3:41:03 PM
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
 *         Qualified Name: org.sjsu.sidmishraw.brickcad.core.TopView
 *
 */
public class TopView extends View {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param model
	 */
	public TopView(Model model, String name) {
		
		super(model, name);
	}
	
	/**
	 * Paints the brick onto the view
	 */
	@Override
	public void paintComponent(Graphics gc) {
		
		Brick brick = (Brick) this.getModel();
		
		gc.setColor(Color.RED);
		gc.fillRect(100, 100, brick.getLength().intValue(), brick.getWidth().intValue());
		gc.setColor(Color.RED);
		gc.drawRect(100, 100, brick.getLength().intValue(), brick.getWidth().intValue());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		this.repaint();
		
		this.setPreferredSize(new Dimension(((Brick) this.getModel()).getLength().intValue() + 350,
				((Brick) this.getModel()).getWidth().intValue() + 250));
		
		// resize the frames depending upon the dimension of the brick
		ViewFrame myparent = ((ViewFrame) SwingUtilities.getAncestorOfClass(ViewFrame.class, this));
		
		myparent.setPreferredSize(this.getPreferredSize());
		
		myparent.pack();
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(((Brick) this.getModel()).getLength().intValue() + 350,
				((Brick) this.getModel()).getWidth().intValue() + 250);
	}
}
