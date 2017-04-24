/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.brickcad.factory
 * File: BrickFactory.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 3:45:41 PM
 */
package org.sjsu.sidmishraw.brickcad.factory;

import java.util.ArrayList;
import java.util.List;

import org.sjsu.sidmishraw.brickcad.core.Brick;
import org.sjsu.sidmishraw.brickcad.views.DimensionView;
import org.sjsu.sidmishraw.brickcad.views.FrontView;
import org.sjsu.sidmishraw.brickcad.views.SideView;
import org.sjsu.sidmishraw.brickcad.views.TopView;
import org.sjsu.sidmishraw.mvc.constants.Constant;
import org.sjsu.sidmishraw.mvc.core.Command;
import org.sjsu.sidmishraw.mvc.core.Model;
import org.sjsu.sidmishraw.mvc.core.View;
import org.sjsu.sidmishraw.mvc.factory.AppFactory;
import org.sjsu.sidmishraw.mvc.util.Utility;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.brickcad.factory.BrickFactory
 *
 */
public class BrickFactory implements AppFactory {
	
	
	// views
	public static final String	SIDE_VIEW		= "Side View";
	public static final String	TOP_VIEW		= "Top View";
	public static final String	FRONT_VIEW		= "Front View";
	public static final String	DIMENSIONS_VIEW	= "Dimensions View";
	
	// commands
	public static final String	SET_WIDTH		= "Set Width";
	public static final String	SET_HEIGHT		= "Set Height";
	public static final String	SET_LENGTH		= "Set Length";
	
	// commands executed from inside the Dimensions view
	public static final String	DIM_SET_WIDTH	= "Dimensions View Set Width";
	public static final String	DIM_SET_HEIGHT	= "Dimensions View Set Height";
	public static final String	DIM_SET_LENGTH	= "Dimensions View Set Length";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sjsu.sidmishraw.mvc.factory.AppFactory#makeModel()
	 */
	@Override
	public Model makeModel() {
		
		return new Brick(0.0, 0.0, 0.0);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sjsu.sidmishraw.mvc.factory.AppFactory#makeView(java.lang.String)
	 */
	@Override
	public View makeView(Model model, String viewType) {
		
		switch (viewType) {
			
			case SIDE_VIEW:
				return new SideView(model, SIDE_VIEW);
			case FRONT_VIEW:
				return new FrontView(model, FRONT_VIEW);
			case TOP_VIEW:
				return new TopView(model, TOP_VIEW);
			case DIMENSIONS_VIEW:
				return new DimensionView(model, DIMENSIONS_VIEW);
		}
		
		return null;
	}
	
	/**
	 * Gets the execution logic for the command depending on the command type
	 * Execution logic is going to be a lambda, having code as data is a great
	 * way according to me.
	 * 
	 * @see
	 * 		org.sjsu.sidmishraw.mvc.factory.AppFactory#makeCommand(java.lang.String)
	 */
	@Override
	public Command makeCommand(String commandType) {
		
		Command command = new Command();
		
		switch (commandType) {
			
			case SET_HEIGHT: {
				
				command.setUndoable(true);
				command.setLogic(() -> {
					
					String newHeight = command.getFromUndoRedoStack() == false ? Utility.askUser("Enter new height")
							: ((Brick) command.getModel()).getHeight().toString();
					
					if (null != newHeight && !newHeight.isEmpty()) {
						
						try {
							
							Double height = Double.valueOf(newHeight);
							
							if (height < 0) {
								
								throw new Exception("Height is negative");
							}
							
							((Brick) command.getModel()).setHeight(height);
						} catch (Exception e) {
							
							Utility.error("Please enter a valid height");
							return false;
						}
						
						return true;
					} else {
						
						Utility.error("Please enter a valid height for the brick");
						return false;
					}
					
				});
				
			}
				break;
			case SET_LENGTH: {
				
				command.setUndoable(true);
				command.setLogic(() -> {
					
					String newLength = command.getFromUndoRedoStack() == false ? Utility.askUser("Enter new length")
							: ((Brick) command.getModel()).getLength().toString();
					
					if (null != newLength && !newLength.isEmpty()) {
						
						try {
							Double length = Double.valueOf(newLength);
							
							if (length < 0) {
								
								throw new Exception("Length is negative");
							}
							
							((Brick) command.getModel()).setLength(length);
						} catch (Exception e) {
							
							Utility.error("Please enter a valid length");
							return false;
						}
						
						return true;
					} else {
						
						Utility.error("Please enter a valid length for the brick");
						return false;
					}
				});
			}
			
				break;
			case SET_WIDTH: {
				
				command.setUndoable(true);
				command.setLogic(() -> {
					
					String newWidth = command.getFromUndoRedoStack() == false ? Utility.askUser("Enter new width")
							: ((Brick) command.getModel()).getWidth().toString();
					
					if (null != newWidth && !newWidth.isEmpty()) {
						
						try {
							Double width = Double.valueOf(newWidth);
							
							if (width < 0) {
								
								throw new Exception("Width is negative");
							}
							
							((Brick) command.getModel()).setWidth(width);
						} catch (Exception e) {
							
							Utility.error("Please enter a valid width");
							return false;
						}
						
						return true;
					} else {
						
						Utility.error("Please enter a valid width for the brick");
						return false;
					}
				});
			}
				break;
			
			case DIM_SET_WIDTH: {
				
				// to be called from the Dimensions view
				command.setUndoable(true);
				command.setLogic(() -> {
					
					Double newWidth = (Double) command.getParamMap().get("WIDTH");
					
					try {
						
						if (newWidth < 0) {
							
							throw new Exception("Please enter a valid width");
						}
						
						if (!command.getFromUndoRedoStack()) {
							
							((Brick) command.getModel()).setWidth(newWidth);
						}
					} catch (Exception e) {
						
						Utility.error("Please enter a valid width");
						return false;
					}
					
					return true;
				});
			}
				break;
			case DIM_SET_LENGTH: {
				
				// to be called from the Dimensions view
				command.setUndoable(true);
				command.setLogic(() -> {
					
					Double newLength = (Double) command.getParamMap().get("LENGTH");
					
					try {
						
						if (newLength < 0) {
							
							throw new Exception("Please enter a valid length");
						}
						
						if (!command.getFromUndoRedoStack()) {
							
							((Brick) command.getModel()).setLength(newLength);
						}
					} catch (Exception e) {
						
						Utility.error("Please enter a valid length");
						return false;
					}
					
					return true;
				});
			}
				break;
			case DIM_SET_HEIGHT: {
				
				// to be called from the Dimensions view
				command.setUndoable(true);
				command.setLogic(() -> {
					
					Double newHeight = (Double) command.getParamMap().get("HEIGHT");
					
					try {
						
						if (newHeight < 0) {
							
							throw new Exception("Please enter a valid height");
						}
						
						if (!command.getFromUndoRedoStack()) {
							
							((Brick) command.getModel()).setHeight(newHeight);
						}
					} catch (Exception e) {
						
						Utility.error("Please enter a valid height");
						return false;
					}
					
					return true;
				});
			}
				break;
		}
		
		return command;
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sjsu.sidmishraw.mvc.factory.AppFactory#getViews()
	 */
	@Override
	public List<String> getViews() {
		
		List<String> views = new ArrayList<>();
		
		views.add(SIDE_VIEW);
		views.add(TOP_VIEW);
		views.add(FRONT_VIEW);
		views.add(DIMENSIONS_VIEW);
		
		return views;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sjsu.sidmishraw.mvc.factory.AppFactory#getCommands()
	 */
	@Override
	public List<String> getCommands() {
		
		List<String> commands = new ArrayList<>();
		
		commands.add(SET_HEIGHT);
		commands.add(SET_LENGTH);
		commands.add(SET_WIDTH);
		
		return commands;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sjsu.sidmishraw.mvc.factory.AppFactory#getTitle()
	 */
	@Override
	public String getTitle() {
		
		return "BrickCAD";
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sjsu.sidmishraw.mvc.factory.AppFactory#getHelp()
	 */
	@Override
	public String getHelp() {
		
		return "You don't need any help with this :)";
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sjsu.sidmishraw.mvc.factory.AppFactory#about()
	 */
	@Override
	public String about() {
		
		return "Brick CAD --  The brick maker v1.0 -- sidmishraw";
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sjsu.sidmishraw.mvc.factory.AppFactory#getFileMenus()
	 */
	@Override
	public List<String> getFileMenus() {
		
		List<String> fileMenus = new ArrayList<>();
		
		fileMenus.add(Constant.NEW);
		fileMenus.add(Constant.OPEN);
		fileMenus.add(Constant.SAVE);
		fileMenus.add(Constant.SAVE_AS);
		fileMenus.add(Constant.QUIT);
		
		return fileMenus;
	}
}
