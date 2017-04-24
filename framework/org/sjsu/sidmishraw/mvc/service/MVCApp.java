/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.service
 * File: MVCApp.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 1:28:31 PM
 */
package org.sjsu.sidmishraw.mvc.service;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.sjsu.sidmishraw.mvc.constants.Constant;
import org.sjsu.sidmishraw.mvc.core.Command;
import org.sjsu.sidmishraw.mvc.core.Model;
import org.sjsu.sidmishraw.mvc.core.View;
import org.sjsu.sidmishraw.mvc.core.ViewFrame;
import org.sjsu.sidmishraw.mvc.factory.AppFactory;
import org.sjsu.sidmishraw.mvc.factory.InstanceFactory;
import org.sjsu.sidmishraw.mvc.util.Utility;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.service.MVCApp
 *
 */
public class MVCApp extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private Model				model;
	private CommandProcessor	commandProcessor;
	private AppFactory			appFactory;
	private JDesktopPane		desktopPane;
	
	/**
	 * Handles the actions on the commands under the edit menu
	 * 
	 * @author sidmishraw
	 *
	 *         Qualified Name: org.sjsu.sidmishraw.mvc.service.EditHandler
	 *
	 */
	private class EditHandler implements ActionListener {
		
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String commandType = e.getActionCommand();
			Command command = appFactory.makeCommand(commandType);
			
			if (null != command) {
				
				command.setModel(model);
				commandProcessor.execute(command);
			} else {
				
				System.out.println("The command was null");
			}
		}
	}
	
	public EditHandler getEditHandler() {
		
		return new EditHandler();
	}
	
	/**
	 * To be passed into the views to handle the view related actions under the
	 * view menu
	 * 
	 * @author sidmishraw
	 *
	 *         Qualified Name: org.sjsu.sidmishraw.mvc.service.ViewHandler
	 *
	 */
	private class ViewHandler implements ActionListener {
		
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String viewType = e.getActionCommand();
			
			View panel = appFactory.makeView(model, viewType);
			
			ViewFrame vf = new ViewFrame(panel);
			
			vf.setVisible(true);
			
			desktopPane.add(vf);
			
			// System.out.println(vf.getLocationOnScreen().toString());
			// System.out.println(vf.getSize());
			// System.out.println(vf.getPreferredSize());
			
			try {
				
				vf.setSelected(true);
			} catch (java.beans.PropertyVetoException ex) {
				
				ex.printStackTrace();
			}
		}
		
	}
	
	public ViewHandler getViewHandler() {
		
		return new ViewHandler();
	}
	
	/**
	 * 
	 */
	public MVCApp() {}
	
	/**
	 * 
	 * @param appFactory
	 */
	private void setupMVCApp(AppFactory appFactory) {
		
		this.appFactory = appFactory;
		this.model = appFactory.makeModel();
		this.commandProcessor = InstanceFactory.getInstance(CommandProcessor.class);
		
		// UI stuff
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		
		// a specialized layered pane
		this.desktopPane = new JDesktopPane();
		
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
		
		// create first "window" here (I forgot to do this)
		
		setContentPane(this.desktopPane);
		setJMenuBar(this.createMenuBar());
		
		// Make dragging a little faster but perhaps uglier.
		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}
	
	protected JMenuBar createMenuBar() {
		// create a menu bar containing File, Edit, View, and Help menus
		
		JMenuBar menuBar = new JMenuBar();
		
		// file menu
		menuBar.add(Utility.makeMenu(Constant.FILE, this.appFactory.getFileMenus(), this));
		
		// edit menu
		JMenu editMenu = Utility.makeMenu(Constant.EDIT, this.appFactory.getCommands(), this.getEditHandler());
		
		// undo and redo are handled by the MVCApp
		editMenu.add(Utility.makeMenuItem(Constant.UNDO, this));
		editMenu.add(Utility.makeMenuItem(Constant.REDO, this));
		
		menuBar.add(editMenu);
		
		// view menu
		menuBar.add(Utility.makeMenu(Constant.VIEW, this.appFactory.getViews(), this.getViewHandler()));
		
		// help and about menus
		menuBar.add(Utility.makeMenu(Constant.HELP_ABOUT, Arrays.asList(new String[] { Constant.HELP, Constant.ABOUT }),
				this));
		
		return menuBar;
	}
	
	/**
	 * Actions under the File menu
	 * 
	 * @see
	 * 		java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
			
			case Constant.SAVE:
				FileMenuService.save(this.model);
				this.commandProcessor.emptyUndoRedoStack();
				return;
			case Constant.SAVE_AS:
				FileMenuService.saveAs(this.model);
				this.commandProcessor.emptyUndoRedoStack();
				return;
			case Constant.OPEN:
				this.model = FileMenuService.open(this.model);
				this.commandProcessor.emptyUndoRedoStack();
				return;
			case Constant.NEW:
				FileMenuService.saveChanges(model);
				this.model = this.appFactory.makeModel();
				this.commandProcessor.emptyUndoRedoStack();
				return;
			case Constant.UNDO:
				System.out.println("Undo was called");
				this.commandProcessor.undo();
				return;
			case Constant.REDO:
				System.out.println("Redo was called");
				this.commandProcessor.redo();
				return;
			case Constant.QUIT:
				FileMenuService.saveChanges(this.model);
				System.exit(DO_NOTHING_ON_CLOSE);
			case Constant.HELP:
				Utility.informUser(this.appFactory.getHelp());
				return;
			case Constant.ABOUT:
				Utility.informUser(this.appFactory.about());
				return;
		}
	}
	
	/**
	 * 
	 * @param factory
	 */
	public static void run(AppFactory factory) {
		
		try {
			
			MVCApp app = InstanceFactory.getInstance(MVCApp.class);
			
			app.setupMVCApp(factory);
			
			System.out.println("Started running the application");
			
			app.setSize(800, 600);
			app.setTitle(factory.getTitle());
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.setVisible(true);
		} catch (Exception e) {
			
			Utility.error("MVCAPP: Error while setup: " + e);
		}
	}
	
	/**
	 * @return the model
	 */
	public Model getModel() {
		return this.model;
	}
	
	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	
	/**
	 * @return the commandProcessor
	 */
	public CommandProcessor getCommandProcessor() {
		return this.commandProcessor;
	}
	
	/**
	 * @param commandProcessor
	 *            the commandProcessor to set
	 */
	public void setCommandProcessor(CommandProcessor commandProcessor) {
		this.commandProcessor = commandProcessor;
	}
	
	/**
	 * @return the appFactory
	 */
	public AppFactory getAppFactory() {
		return this.appFactory;
	}
	
	/**
	 * @param appFactory
	 *            the appFactory to set
	 */
	public void setAppFactory(AppFactory appFactory) {
		this.appFactory = appFactory;
	}
	
	/**
	 * @return the desktopPane
	 */
	public JDesktopPane getDesktopPane() {
		return this.desktopPane;
	}
	
	/**
	 * @param desktopPane
	 *            the desktopPane to set
	 */
	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}
	
}
