package utility.sql;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import utility.sql.Sql.Null;

public class SqlTest
{
    @Test
    public void testAddSimple () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
    
        stmt.clearParameters ();
        stmt.setInt ( 1, 1 );
        stmt.setLong ( 2, 2 );
        stmt.setDouble ( 3, 3.3 );
        stmt.setBoolean ( 4, true );
        stmt.setString ( 5, "Five" );
        stmt.setNull ( 6, java.sql.Types.BOOLEAN );
        stmt.setNull ( 7, java.sql.Types.VARCHAR );
        
        EasyMock.replay ( stmt );
        
        new Sql ( stmt ).add ( 1 ).add ( 2L ).add ( 3.3 ).add ( true ).add ( "Five" ).add ( Null.BOOLEAN ).add ( Null.STRING );
    }
    
    @Test
    public void testAddConstructor () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
    
        stmt.clearParameters ();
        stmt.setInt ( 1, 1 );
        stmt.setLong ( 2, 2 );
        stmt.setDouble ( 3, 3.3 );
        stmt.setBoolean ( 4, true );
        stmt.setString ( 5, "Five" );
        stmt.setNull ( 6, java.sql.Types.BOOLEAN );
        stmt.setNull ( 7, java.sql.Types.VARCHAR );
        
        EasyMock.replay ( stmt );
        
        new Sql ( stmt, 1, 2L, 3.3, true, "Five", Null.BOOLEAN, Null.STRING );
    }
    
    @Test
    public void testAddArray () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
    
        stmt.clearParameters ();
        stmt.setInt ( 1, 1 );
        stmt.setLong ( 2, 2 );
        stmt.setDouble ( 3, 3.3 );
        stmt.setBoolean ( 4, true );
        stmt.setString ( 5, "Five" );
        stmt.setNull ( 6, java.sql.Types.BOOLEAN );
        stmt.setNull ( 7, java.sql.Types.VARCHAR );
        
        EasyMock.replay ( stmt );
        
        new Sql ( stmt ).add ( 1, 2L, 3.3, true, "Five", Null.BOOLEAN, Null.STRING );
    }
    
    @Test
    public void testAddObjectArray () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
    
        stmt.clearParameters ();
        stmt.setString ( 1, "Fred" );
        stmt.setString ( 2, "Barney" );
        
        EasyMock.replay ( stmt );
        
        List<String> l = new ArrayList<>();
        l.add ( "Fred" );
        l.add ( "Barney" );
        
        new Sql ( stmt ).add ( l.toArray () );
    }
    
    @Test
    public void testAddGenericList () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
    
        stmt.clearParameters ();
        stmt.setInt ( 1, 1 );
        stmt.setDouble ( 2, 2.2 );
        
        EasyMock.replay ( stmt );
        
        new Sql ( stmt ).add ( Arrays.asList ( 1, 2.2 ) );
    }
    
    @Test
    public void testAddIntList () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
    
        stmt.clearParameters ();
        stmt.setInt ( 1, 7 );
        stmt.setInt ( 2, 11 );
        
        EasyMock.replay ( stmt );
        
        List<Integer> l = new ArrayList<>();
        l.add ( 7 );
        l.add ( 11 );
        
        new Sql ( stmt ).add ( l );
    }
    
    @Test
    public void testClear() throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
        
        stmt.clearParameters ();
        stmt.setInt ( 1, 1 );
        stmt.setLong ( 2, 2 );
        stmt.setDouble ( 3, 3.3 );
        stmt.setBoolean ( 4, true );
        stmt.clearParameters ();
        stmt.setString ( 1, "Five" );
        stmt.setNull ( 2, java.sql.Types.BOOLEAN );
        stmt.setNull ( 3, java.sql.Types.VARCHAR );
        
        EasyMock.replay ( stmt );
        
        new Sql ( stmt ).add ( 1 ).add ( 2L ).add ( 3.3 ).add ( true ).clear ().add ( "Five" ).add ( Null.BOOLEAN ).add ( Null.STRING );
    }
    
    @Test
    public void testUpdate () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
        
        stmt.clearParameters ();
        stmt.execute ();
        EasyMock.expectLastCall ().andReturn ( false );
        
        EasyMock.replay ( stmt );
        
        assertNull ( new Sql ( stmt ).go () );
    }
    
    @Test
    public void testUpdateWithParams () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
        
        stmt.clearParameters ();
        EasyMock.expectLastCall ().times ( 2 );
        
        stmt.setInt ( 1, 7 );
        stmt.setString ( 2, "Jackie" );
        stmt.execute ();
        EasyMock.expectLastCall ().andReturn ( false );
        
        EasyMock.replay ( stmt );
        
        assertNull ( new Sql ( stmt ).go ( 7, "Jackie" ) );
    }
    
    @Test
    public void testUpdateWithParamsOverride () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
        
        stmt.clearParameters ();
        EasyMock.expectLastCall ().times ( 2 );
        
        stmt.setBoolean ( 1, false );
        
        stmt.setInt ( 1, 7 );
        stmt.setString ( 2, "Jackie" );
        stmt.execute ();
        EasyMock.expectLastCall ().andReturn ( false );
        
        EasyMock.replay ( stmt );
        
        assertNull ( new Sql ( stmt ).add ( false ).go ( 7, "Jackie" ) );
    }
    
    @Test
    public void testUpdateWithList () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
        
        stmt.clearParameters ();
        EasyMock.expectLastCall ().times ( 2 );
        
        stmt.setLong ( 1, 7L );
        stmt.setLong ( 2, 11L );
        stmt.execute ();
        EasyMock.expectLastCall ().andReturn ( false );
        
        EasyMock.replay ( stmt );
        
        List<Long> l = new ArrayList<>();
        l.add ( 7L );
        l.add ( 11L );
        
        assertNull ( new Sql ( stmt ).go ( l ) );
    }
    
    @Test
    public void testQuery () throws SQLException
    {
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        stmt.clearParameters ();
        EasyMock.expect ( stmt.execute () ).andReturn ( true );
        EasyMock.expect ( stmt.getResultSet () ).andReturn ( rs );
        EasyMock.expect ( rs.next () ).andReturn ( true );
        
        EasyMock.replay ( stmt, rs );
        
        assertNotNull ( new Sql ( stmt ).go () );
    }
}
