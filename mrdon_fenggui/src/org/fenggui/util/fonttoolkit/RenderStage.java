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
 * Created on Jan 31, 2006
 * $Id: RenderStage.java 28 2006-10-05 01:37:07Z schabby $
 */
package org.fenggui.util.fonttoolkit;

import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

/**
 * 
 * 
 * @todo Comment this class... #
 * 
 * @author Johannes Schaback, last edited by $Author: schabby $, $Date: 2006-10-05 03:37:07 +0200 (Thu, 05 Oct 2006) $
 * @version $Revision: 28 $
 */
public class RenderStage {

	public void renderChar(FontMetrics fontMetrics, BufferedImage image, char c, final int safetyMargin) {
		renderChar(fontMetrics, image, c);
	}
	
	public void renderChar(FontMetrics fontMetrics, BufferedImage image, char c) {
		renderChar(image);
	}
	
	public void renderChar(BufferedImage image) {
		// does nothing... 
	}
}
