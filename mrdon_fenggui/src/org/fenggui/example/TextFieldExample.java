/*
 * FengGUI - Java GUIs in OpenGL (http://www.fenggui.org)
 * 
 * Copyright (c) 2005, 2006 FengGUI Project
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details:
 * http://www.gnu.org/copyleft/lesser.html#TOC3
 * 
 * $Id: TextFieldExample.java 28 2006-10-05 01:37:07Z schabby $
 */
package org.fenggui.example;

import org.fenggui.*;

/**
 * Demonstrates a <code>TextField</code> Widget.
 * 
 * 
 * @author Johannes Schaback
 * 
 */
public class TextFieldExample implements IExample {

    private Display desk;
    
	public void buildGUI(Display g) {
		desk = g;
		
		TextEditor tf = new TextEditor();
		desk.addWidget(tf);
    	
    	tf.setX(50);
    	tf.setY(50);
    	
    	tf.setText("Enter Some Text Here...");
    	tf.setSizeToMinSize();
	}

	public String getExampleName() {
		return "Text Field Example";
	}

	public String getExampleDescription() {
		return "Text Field Example";
	}
    
}
