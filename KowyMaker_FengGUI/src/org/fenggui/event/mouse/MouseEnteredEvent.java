/*
 * FengGUI - Java GUIs in OpenGL (http://www.fenggui.org)
 * 
 * Copyright (c) 2005-2009 FengGUI Project
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
 * Created on Oct 8, 2006
 * $Id: MouseEnteredEvent.java 606 2009-03-13 14:56:05Z marcmenghin $
 */
package org.fenggui.event.mouse;

import java.util.Set;

import org.fenggui.IWidget;
import org.fenggui.event.key.Key;

public class MouseEnteredEvent extends MouseEvent
{
    private IWidget           entered = null;
    private IWidget           exited  = null;
    private final int         displayX;
    private final int         displaY;
    private final int         clickCount;
    private final MouseButton mouseButton;
    
    public MouseEnteredEvent(IWidget entered, IWidget exited, int displayX,
            int displaY, MouseButton mouseButton, int clickCount,
            Set<Key> modifiers)
    {
        super(entered, modifiers);
        this.entered = entered;
        this.exited = exited;
        this.displayX = displayX;
        this.displaY = displaY;
        this.mouseButton = mouseButton;
        this.clickCount = clickCount;
    }
    
    public IWidget getEntered()
    {
        return entered;
    }
    
    public IWidget getExited()
    {
        return exited;
    }
    
    @Override
    public int getDisplayX()
    {
        return displayX;
    }
    
    @Override
    public int getDisplayY()
    {
        return displaY;
    }
    
    public MouseButton getButton()
    {
        return mouseButton;
    }
    
    public int getClickCount()
    {
        return clickCount;
    }
}
