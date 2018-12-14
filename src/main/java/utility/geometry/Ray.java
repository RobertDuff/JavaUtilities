package utility.geometry;

/**
 * @deprecated
 * This class represents a Ray.
 * 
 * @author Rob Duff
 *
 */
@Deprecated
public class Ray
{
	private double theta;
	private Point  anchor;
	
	@Deprecated
	public double theta()
	{
		return theta;
	}
	
	@Deprecated
	public void theta ( double theta )
	{
		this.theta = theta;
	}
	
	@Deprecated
	public void theta ( double y, double x )
	{
		theta = Math.atan2 ( y, x );
	}
	
	@Deprecated
	public void theta ( Point a, Point b )
	{
		theta = Math.atan2 ( b.y() - a.y(), b.x() - a.x() );
	}
	
	@Deprecated
	public Point anchor()
	{
		return anchor;
	}
	
	@Deprecated
	public void anchor ( Point point )
	{
		anchor = new Point ( point );
	}
	
	@Deprecated
	public void anchor ( double x, double y )
	{
		anchor = new Point ( x, y );
	}
	
	@Deprecated
	public double x()
	{
		return anchor.x();
	}
	
	@Deprecated
	public double y()
	{
		return anchor.y();
	}
}
