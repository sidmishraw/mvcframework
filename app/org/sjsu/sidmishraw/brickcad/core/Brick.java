/**
 * Project: MVCFramework
 * Package: org.sjsu.sidmishraw.brickcad.core
 * File: Brick.java
 * 
 * @author sidmishraw
 *         Last modified: Apr 20, 2017 3:40:03 PM
 */
package org.sjsu.sidmishraw.brickcad.core;

import org.sjsu.sidmishraw.mvc.core.Model;

/**
 * @author sidmishraw
 *
 *         Qualified Name: org.sjsu.sidmishraw.brickcad.core.Brick
 *
 */
public class Brick extends Model {
	
	
	private Double				length;
	private Double				width;
	private Double				height;
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * 
	 */
	public Brick() {
		
		super();
	}
	
	/**
	 * @param length
	 * @param width
	 * @param height
	 */
	public Brick(Double length, Double width, Double height) {
		
		super();
		
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * @param fileName
	 * @param unsavedChanges
	 */
	public Brick(String fileName, Boolean unsavedChanges) {
		
		super(fileName, unsavedChanges);
	}
	
	/**
	 * @return the length
	 */
	public Double getLength() {
		return this.length;
	}
	
	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(Double length) {
		this.length = length;
	}
	
	/**
	 * @return the width
	 */
	public Double getWidth() {
		return this.width;
	}
	
	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Double width) {
		this.width = width;
	}
	
	/**
	 * @return the height
	 */
	public Double getHeight() {
		return this.height;
	}
	
	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * For debugging
	 */
	@Override
	public String toString() {
		
		return "Brick [length=" + this.length + ", width=" + this.width + ", height=" + this.height + "]";
	}
}
