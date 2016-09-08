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
 * $Id: XMLTest.java 327 2007-08-11 11:20:15Z Schabby $
 */
package org.fenggui.example;

import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LEQUAL;
import static javax.media.opengl.GL.GL_LIGHTING;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import org.fenggui.ComboBox;
import org.fenggui.Display;
import org.fenggui.Label;
import org.fenggui.List;
import org.fenggui.background.PlainBackground;
import org.fenggui.border.PlainBorder;
import org.fenggui.composites.Window;
import org.fenggui.render.jogl.EventBinding;
import org.fenggui.render.jogl.JOGLBinding;
import org.fenggui.theme.xml.IXMLStreamableException;
import org.fenggui.theme.xml.XMLInputStream;

import com.sun.opengl.util.Animator;


/**
 * Base class for all test classes and example
 * classes. It opens a Swing Frame and initialized
 * the JOGL-Binding.
 * 
 * @author Johannes, last edited by $Author: Schabby $, $Date: 2007-08-11 13:20:15 +0200 (Sa, 11 Aug 2007) $
 * @version $Revision: 327 $
 */
public class XMLTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private GL gl;
	private GLU glu = new GLU();
    private GLCanvas canvas;
    private Display display = null;
    private GLEventListener eventListener = null;
    private float rotAngle = 0;
    private boolean runsAsWebstart = false; 
    private FengCharacterRenderer fengRenderer = null;
    
    public XMLTest()
    {
    	this(false);
    }
    
    public XMLTest(boolean runsAsWebstart)
    {
    	this.runsAsWebstart = runsAsWebstart;
    	
    	canvas = new GLCanvas();
        
        eventListener = new GLEventListenerImplementation();
        
        canvas.addGLEventListener(eventListener);
        
        getContentPane().add(canvas, java.awt.BorderLayout.CENTER);
        setSize(800, 800);
        setTitle("FengGUI - Test Template");

        Animator animator = new Animator(canvas);
        animator.start();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public final void setVisible(boolean b) {
        super.setVisible(b);
       
    }

    public GLCanvas getCanvas() {
    	return canvas;
    }
    
    private class GLEventListenerImplementation implements GLEventListener 
    {
    	
        /*
         * (non-Javadoc)
         * 
         * @see net.java.games.jogl.GLEventListener#display(net.java.games.jogl.GLDrawable)
         */
        public void display(GLAutoDrawable arg0)
        {
            gl.glLoadIdentity();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            
            
            glu.gluLookAt(0, 0, 800, 
            		0, 230, 0, 
            		0, 0, 1);

            gl.glPushMatrix();
            gl.glRotatef(rotAngle, 0, 1, 0);
           // fengRenderer.display();
            gl.glPopMatrix();
            
            rotAngle  += 0.05;
            
            display.display();
        }

        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
        {
            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL.GL_PROJECTION);
            gl.glLoadIdentity();
            glu.gluPerspective(45, (double)width/(double)height, 4, 1000);
            gl.glMatrixMode(GL.GL_MODELVIEW);
            gl.glLoadIdentity();
            

            //
        }
        
        public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        	// does nothing
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see net.java.games.jogl.GLEventListener#init(net.java.games.jogl.GLDrawable)
         */
        public void init(GLAutoDrawable drawable)
        {
            gl = drawable.getGL();
            gl.glClearColor(146f/255f, 164f/255f, 1, 0.0f);
            gl.glEnable(GL.GL_BLEND);
            
            gl.glDisable(GL.GL_TEXTURE_2D);
            gl.glEnable(GL_DEPTH_TEST);
            gl.glDepthFunc(GL_LEQUAL);
            gl.glShadeModel(GL.GL_SMOOTH);
            gl.glEnable(GL_LIGHTING);
            gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, new float[] {146f/255f, 164f/255f, 1f, 1.0f}, 0);
            gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, new float[] {1f, 1f, 1f, 1.0f}, 0);
            gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, new float[] {-100, -100, 400}, 0);
            gl.glEnable(GL.GL_LIGHT1);
            
          //  fengRenderer = new FengCharacterRenderer(gl);
            
            buildGUI();
            
        }

    }
    
    public static void main(String[] args) 
    {
        XMLTest f = new XMLTest(args.length > 0);
        f.setTitle("FengGUI - Test Almost Everything");
        f.setVisible(true);
    }
    
	public void buildGUI()
	{
		setDisplay(new Display(new JOGLBinding(canvas)));

		new EventBinding(getCanvas(), getDisplay());
		
		new Label();
		new PlainBorder();
		new ComboBox();
		new List();
		new PlainBackground();
		new Window();
		
		try
		{
			XMLInputStream xis = new XMLInputStream(new FileInputStream("data/xml/SimpleLayout.xml"));
			getDisplay().process(xis);
			
			getDisplay().addedToWidgetTree();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (IXMLStreamableException e)
		{
			e.printStackTrace();
		}
        getDisplay().layout();
	}

    public GL getGL() {
    	return gl;
    }
    
    public GLU getGLU() {
    	return glu;
    }


	public Display getDisplay() {
		return display;
	}


	public void setDisplay(Display display) {
		this.display = display;
	}    

    
}
