package utility.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class Results implements Iterator<ResultSet>, Iterable<ResultSet>
{
    private ResultSet results;
    private boolean singleRow;
    
    public Results ( ResultSet rs )
    {
        results = rs;
        singleRow = false;
    }        

    public ResultSet single() throws SQLException
    {
        if ( !singleRow )
        {
            if ( !results.next () )
                return null;
                        
            singleRow = true;
        }   
        
        return results;
    }
    
    @Override
    public Iterator<ResultSet> iterator ()
    {
        return this;
    }

    @Override
    public boolean hasNext ()
    {
        try
        {
            return results.next ();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public ResultSet next ()
    {
        try
        {
            if ( results.isBeforeFirst () )
                return null;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            return null;
        }
        
        return results;
    }
}
