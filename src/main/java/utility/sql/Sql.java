package utility.sql;

/**
 * This class 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql
{
    public static enum Null
    {
        INTEGER ( java.sql.Types.INTEGER ),
        LONG ( java.sql.Types.INTEGER ),
        DOUBLE ( java.sql.Types.DOUBLE ),
        STRING ( java.sql.Types.VARCHAR ),
        BOOLEAN ( java.sql.Types.BOOLEAN );
        
        private int type;
        
        private Null ( int type )
        {
            this.type = type;
        }
        
        public int type()
        {
            return type;
        }
    };
    
    Connection connection;
    
    PreparedStatement statement;
    int parameterIndex;
    
    public Sql ()
    {
    }
    
    public Sql ( Connection conn )
    {
        connection ( conn );
    }

    public Sql ( Connection conn, String statement ) throws SQLException
    {
        this ( conn );
        statement ( statement );
    }
    
    public Sql ( Connection conn, String statement, Object... parameters ) throws SQLException
    {
        this ( conn, statement );
        add ( parameters );
    }
    
    public Sql ( PreparedStatement statement ) throws SQLException
    {
        statement ( statement );
        statement.clearParameters ();
    }
    
    public Sql ( PreparedStatement statement, Object... parameters ) throws SQLException
    {
        this ( statement );
        add ( parameters );
    }
    
    public Sql connection ( Connection connection )
    {
        this.connection = connection;
        return this;
    }
    
    public Sql statement ( String sql ) throws SQLException
    {
        statement = connection.prepareStatement ( sql );
        parameterIndex = 1;
        return this;
    }
    
    public Sql statement ( PreparedStatement statement ) throws SQLException
    {
        this.statement = statement;
        parameterIndex = 1;
        return this;
    }
    
    public Sql add ( int value ) throws SQLException
    {
        statement.setInt ( parameterIndex++, value );
        return this;
    }
    
    public Sql add ( long value ) throws SQLException
    {
        statement.setLong ( parameterIndex++, value );
        return this;
    }
    
    public Sql add ( double value ) throws SQLException
    {
        statement.setDouble ( parameterIndex++, value );
        return this;
    }
    
    public Sql add ( String value ) throws SQLException
    {
        statement.setString ( parameterIndex++, value );
        return this;
    }
    
    public Sql add ( boolean value ) throws SQLException
    {
        statement.setBoolean ( parameterIndex++, value );
        return this;
    }

    public Sql add ( Null type ) throws SQLException
    {
        statement.setNull ( parameterIndex++, type.type() );
        return this;
    }
    
    protected Sql addObject ( Object value ) throws SQLException
    {
        if ( value instanceof Integer )
            statement.setInt ( parameterIndex++, ( Integer ) value );
        else if ( value instanceof Long )
            statement.setLong ( parameterIndex++, ( Long ) value );
        else if ( value instanceof Double )
            statement.setDouble ( parameterIndex++, ( Double ) value );
        else if ( value instanceof Boolean )
            statement.setBoolean ( parameterIndex++, ( Boolean ) value );
        else if ( value instanceof String )
            statement.setString ( parameterIndex++, ( String ) value );
        else if ( value instanceof Null )
            statement.setNull ( parameterIndex++, ( ( Null ) value ).type () );
        else if ( value instanceof Iterable )
        {
            for ( Object o : ( Iterable<?> ) value )
                add ( o );
        }
        else
            throw new IllegalArgumentException ( "Cannot add Object of Type: " + value.getClass ().getCanonicalName () );

        return this;
    }

    public Sql add ( Object... values ) throws SQLException
    {
        for ( Object value : values )
            addObject ( value );
        
        return this;
    }
        
    public Sql clear() throws SQLException
    {
        statement.clearParameters ();
        parameterIndex = 1;
        return this;
    }
    
    public Results go() throws SQLException
    {
        if ( statement.execute () )
            return new Results ( statement.getResultSet () );
        
        return null;
    }
    
    public Results go ( Object... parameters ) throws SQLException
    {
        clear();
        add ( parameters );
        return go();
    }
}
