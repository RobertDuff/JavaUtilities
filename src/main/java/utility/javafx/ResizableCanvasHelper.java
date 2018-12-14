package utility.javafx;

import javafx.scene.canvas.Canvas;

/**
 * Helper Object for {@link ResizableCanvas}.  This object actually does the canvas redrawing.
 * @author Robert Duff
 *
 */
public interface ResizableCanvasHelper
{
	/**
	 * Re-Draws the contents of a {@link ResizableCanvas}
	 * @param canvas The {@link ResizableCanvas} to draw within.
	 */
	public void draw ( Canvas canvas );
}
