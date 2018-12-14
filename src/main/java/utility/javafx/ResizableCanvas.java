package utility.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 * Resizable Canvas for JAVA FX.  This class uses a {@link ResizableCanvasHelper} to actually draw the canvas content.
 * @author Robert Duff
 *
 */
public class ResizableCanvas extends Canvas
{
	protected ResizableCanvasHelper helper = null;
	
	/**
	 * Create a Resizable Canvas with no helper.
	 * @param parent The parent {@link Pane} containing this {@code ResizableCanvas}
	 */
	public ResizableCanvas ( Pane parent )
	{
		widthProperty().addListener ( evt -> draw() );
		heightProperty().addListener ( evt -> draw() );

		widthProperty ().bind ( parent.widthProperty () );
		heightProperty ().bind ( parent.heightProperty () );
		
		parent.getChildren ().add ( this );
	}
	
	/**
	 * Create a Resizable Canvas with a {@link ResizableCanvasHelper} helper.
	 * @param parent The parent {@link Pane} containing this {@code ResizableCanvas}
	 * @param helper The helper object
	 */
	public ResizableCanvas ( Pane parent, ResizableCanvasHelper helper )
	{
		this ( parent );
		this.helper = helper;
	}
	
	/**
	 * @param helper - A drawing helper for the Resizable Canvas
	 */
	public void setHelper ( ResizableCanvasHelper helper )
	{
		this.helper = helper;
	}
	
  @Override
  public boolean isResizable() 
  {
    return true;
  }
  
  /**
   * Re-Draws the contents of the canvas.  This method delegates all responsibilities to a {@link ResizableCanvasHelper}.
   */
  public void draw()
  {
  	if ( helper != null )
  		helper.draw ( this );
  }
}
