/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.mvc.util
 * File: Utility.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 1:57:04 PM
 */
package org.sjsu.sidmishraw.mvc.util;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.mvc.util.Utility
 *
 */
public class Utility {
	
	
	private static Utility utility = null;
	
	/**
	 * 
	 */
	private Utility() {}
	
	public static Utility getInstance() {
		
		if (null == utility) {
			
			utility = new Utility();
		}
		
		return utility;
	}
	
	/**
	 * Make a menu like File, Help etc
	 * 
	 * @param name
	 *            - name of the menu
	 * @param items
	 *            - name of the items in the menu
	 * @param handler
	 *            - action handler
	 * @return {@link JMenu}
	 */
	public static JMenu makeMenu(String name, List<String> items, ActionListener handler) {
		
		JMenu result = new JMenu();
		
		int j = name.indexOf('&');
		
		if (j != -1) {
			
			char c = name.charAt(j + 1);
			name = name.substring(0, j) + name.substring(j + 1);
			result.setMnemonic(c);
		}
		
		result.setName(name);
		result.setText(name);
		
		items.stream().forEach(item -> {
			
			if (null == item) {
				
				result.addSeparator();
			} else {
				
				int indexAmp = item.indexOf('&');
				
				JMenuItem menuItem = null;
				
				if (indexAmp != -1) {
					
					char c = item.charAt(indexAmp + 1);
					String s = item.substring(0, indexAmp) + item.substring(indexAmp + 1);
					menuItem = new JMenuItem(s, item.charAt(indexAmp + 1));
					menuItem.setAccelerator(KeyStroke.getKeyStroke(c, InputEvent.CTRL_MASK));
				} else {
					
					menuItem = new JMenuItem(item);
				}
				
				menuItem.addActionListener(handler);
				result.add(menuItem);
			}
		});
		
		return result;
	}
	
	/**
	 * Makes a new menuitem for the given name
	 * 
	 * @param menuItemName
	 * @param eventHandler
	 * @return {@link JMenuItem}
	 */
	public static JMenuItem makeMenuItem(String menuItemName, ActionListener eventHandler) {
		
		JMenuItem menuItem = new JMenuItem(menuItemName);
		
		menuItem.addActionListener(eventHandler);
		
		return menuItem;
	}
	
	/**
	 * Prompts the user
	 * 
	 * @param query
	 * @return String
	 */
	public static String askUser(String query) {
		
		return JOptionPane.showInputDialog(query);
	}
	
	/**
	 * 
	 * @param query
	 * @return {@link Boolean}
	 */
	public static boolean confirm(String query) {
		
		int result = JOptionPane.showConfirmDialog(null, query, "choose one", JOptionPane.YES_NO_OPTION);
		
		return result == 0;
	}
	
	/**
	 * Error popup
	 * 
	 * @param gripe
	 */
	public static void error(String gripe) {
		
		JOptionPane.showMessageDialog(null, gripe, "OOPS!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * 
	 * @param gripe
	 */
	public static void error(Exception gripe) {
		
		gripe.printStackTrace();
		JOptionPane.showMessageDialog(null, gripe.toString(), "OOPS!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * 
	 * @param info
	 */
	public static void informUser(String info) {
		
		JOptionPane.showMessageDialog(null, info, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
