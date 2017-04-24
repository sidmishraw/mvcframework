/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.brickcad.core
 * File: DimensionView.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 3:41:32 PM
 */
package org.sjsu.sidmishraw.brickcad.views;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.sjsu.sidmishraw.brickcad.core.Brick;
import org.sjsu.sidmishraw.brickcad.factory.BrickFactory;
import org.sjsu.sidmishraw.mvc.core.Command;
import org.sjsu.sidmishraw.mvc.core.Model;
import org.sjsu.sidmishraw.mvc.core.View;
import org.sjsu.sidmishraw.mvc.factory.InstanceFactory;
import org.sjsu.sidmishraw.mvc.service.CommandProcessor;
import org.sjsu.sidmishraw.mvc.util.SpringUtilities;
import org.sjsu.sidmishraw.mvc.util.Utility;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.brickcad.core.DimensionView
 *
 */
public class DimensionView extends View {
	
	
	private JLabel				widthLabel			= null;
	private JLabel				heightLabel			= null;
	private JLabel				lengthLabel			= null;
	private JTextField			width				= null;
	private JTextField			height				= null;
	private JTextField			length				= null;
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * @param model
	 */
	public DimensionView(Model model, String name) {
		
		super(model, name);
		
		this.draw();
	}
	
	private void draw() {
		
		if (null == this.widthLabel) {
			
			this.widthLabel = new JLabel("Width: ");
		}
		
		if (null == this.lengthLabel) {
			
			this.lengthLabel = new JLabel("Length: ");
		}
		
		if (null == this.heightLabel) {
			
			this.heightLabel = new JLabel("Height: ");
		}
		
		if (null == this.width) {
			
			this.width = new JTextField(((Brick) this.getModel()).getWidth().toString());
		} else {
			
			this.width.setText(((Brick) this.getModel()).getWidth().toString());
		}
		
		if (null == this.height) {
			
			this.height = new JTextField(((Brick) this.getModel()).getHeight().toString());
		} else {
			
			this.height.setText(((Brick) this.getModel()).getHeight().toString());
		}
		
		if (null == this.length) {
			
			this.length = new JTextField(((Brick) this.getModel()).getLength().toString());
		} else {
			
			this.length.setText(((Brick) this.getModel()).getLength().toString());
		}
		
		this.height.setEditable(true);
		this.height.setEnabled(true);
		this.length.setEditable(true);
		this.length.setEnabled(true);
		this.width.setEditable(true);
		this.width.setEnabled(true);
		
		SpringLayout dimensionViewLayout = new SpringLayout();
		
		this.setLayout(dimensionViewLayout);
		
		// Title statement
		this.add(new JLabel("Brick Attributes"));
		this.add(new JLabel(""));
		
		// add the components
		this.add(this.widthLabel);
		this.add(width);
		this.widthLabel.setLabelFor(width);
		
		this.add(this.lengthLabel);
		this.add(length);
		this.lengthLabel.setLabelFor(length);
		
		this.add(this.heightLabel);
		this.add(height);
		this.heightLabel.setLabelFor(height);
		
		this.add(new JLabel("Type text in the field and press Enter"));
		this.add(new JLabel(""));
		
		// specify positions for the layout
		SpringUtilities.makeCompactGrid(this, 5, 2, 30, 30, 5, 5);
		
		// to be used inside inner annonymous class definitions
		// sad looking workaround, but this seems more feasible to me in Java
		DimensionView me = this;
		
		// adding listener for width changes
		this.width.addKeyListener(new KeyListener() {
			
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				// propagate the event only after the return/enter key is
				// released
				System.out.println(KeyEvent.getKeyText(e.getKeyCode()) + " Key pressed on length box");
				
				// enter key
				if (e.getKeyCode() == 10) {
					
					Double newWidth = 0.0;
					
					try {
						
						newWidth = Double.valueOf(me.width.getText());
						
						// execute the setwidth command
						Command command = InstanceFactory.getInstance(BrickFactory.class)
								.makeCommand(BrickFactory.DIM_SET_WIDTH);
						
						// set the flag to bypass the user input
						command.setModel(((Brick) me.getModel()));
						command.getParamMap().put("WIDTH", newWidth);
						
						InstanceFactory.getInstance(CommandProcessor.class).execute(command);
						
						// set the new updated model in here to keep it up to
						// date
						me.setModel(command.getModel());
					} catch (Exception ex) {
						
						Utility.error("Invalid width entered");
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		// adding listener for length changes
		this.length.addKeyListener(new KeyListener() {
			
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				System.out.println(KeyEvent.getKeyText(e.getKeyCode()) + " Key pressed on length box");
				
				if (e.getKeyCode() == 10) {
					
					Double newLength = 0.0;
					
					try {
						
						newLength = Double.valueOf(me.length.getText());
						
						// execute the SetLength command
						Command command = InstanceFactory.getInstance(BrickFactory.class)
								.makeCommand(BrickFactory.DIM_SET_LENGTH);
						
						// set the flag to bypass the user input
						command.setModel(((Brick) me.getModel()));
						command.getParamMap().put("LENGTH", newLength);
						
						InstanceFactory.getInstance(CommandProcessor.class).execute(command);
						
						// set the new updated model in here to keep it up to
						// date
						me.setModel(command.getModel());
					} catch (Exception ex) {
						
						Utility.error("Invalid length entered");
					}
					
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		// adding listener for height changes
		this.height.addKeyListener(new KeyListener() {
			
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				System.out.println(KeyEvent.getKeyText(e.getKeyCode()) + " Key pressed on length box");
				
				if (e.getKeyCode() == 10) {
					
					Double newHeight = 0.0;
					
					try {
						
						newHeight = Double.valueOf(me.height.getText());
						
						// execute the SetHeight command
						Command command = InstanceFactory.getInstance(BrickFactory.class)
								.makeCommand(BrickFactory.DIM_SET_HEIGHT);
						
						// set the flag to bypass the user input
						command.setModel(((Brick) me.getModel()));
						command.getParamMap().put("HEIGHT", newHeight);
						
						InstanceFactory.getInstance(CommandProcessor.class).execute(command);
						
						// set the new updated model in here to keep it up to
						// date
						me.setModel(command.getModel());
					} catch (Exception ex) {
						
						Utility.error("Invalid height entered");
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		System.out.println("HARMLESS:: udpate dim view: model id = " + ((Brick) this.getModel()));
		this.repaint();
		
		this.length.setText(((Brick) this.getModel()).getLength().toString());
		this.width.setText(((Brick) this.getModel()).getWidth().toString());
		this.height.setText(((Brick) this.getModel()).getHeight().toString());
	}
}
