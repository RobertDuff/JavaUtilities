package utility.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultsTest
{
    @Test
    public void testNoResults_Single () throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        EasyMock.expect ( rs.next () ).andReturn ( false );

        EasyMock.replay ( md, rs );
        
        Assertions.assertNull ( new Results ( rs ).single() );

        EasyMock.verify ( md, rs );
    }
    
    @Test
    public void testNoResults_Iterator() throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );

        EasyMock.expect ( rs.next () ).andReturn ( false );
        
        EasyMock.replay ( md, rs );
                
        Assertions.assertFalse ( new Results ( rs ).iterator ().hasNext () );

        EasyMock.verify ( md, rs );
    }
    
    @Test
    public void testNoResults_Loop() throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );

        EasyMock.expect ( rs.next () ).andReturn ( false );
        
        EasyMock.replay ( md, rs );
        
        for ( @SuppressWarnings ( "unused" ) ResultSet row : new Results ( rs ) )
            Assertions.fail ( "Should not have succeeded" );

        EasyMock.verify ( md, rs );
    }
    
    @Test
    public void testGetInt_1_Row_1_Column() throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 77 );
        
        EasyMock.replay ( md, rs );
        
        Results r = new Results ( rs );
        
        Assertions.assertEquals ( 77, r.single ().getInt ( 1 ) );

        EasyMock.verify ( md, rs );
    }
    
    @Test
    public void testGetInt_1_Row_2_Columns() throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 77 );
        EasyMock.expect ( rs.getInt ( 2 ) ).andReturn ( 88 );
        
        EasyMock.replay ( md, rs );
        
        Results r = new Results ( rs );
        
        Assertions.assertEquals ( 77, r.single ().getInt ( 1 ) );
        Assertions.assertEquals ( 88, r.single ().getInt ( 2 ) );

        EasyMock.verify ( md, rs );
    }
    
    @Test
    public void testGetInt_2_Rows_1_Column_Iterator() throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );
        EasyMock.expect ( rs.isBeforeFirst () ).andReturn ( false );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 77 );

        EasyMock.expect ( rs.next () ).andReturn ( true );
        EasyMock.expect ( rs.isBeforeFirst () ).andReturn ( false );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 88 );
        
        EasyMock.expect ( rs.next () ).andReturn ( false );
        
        EasyMock.replay ( md, rs );
        
        Results r = new Results ( rs );
        
        if ( r.hasNext () )
            Assertions.assertEquals ( 77, r.next ().getInt ( 1 ) );
        else
            Assertions.fail ( "Should not have next" );

        if ( r.hasNext () )
            Assertions.assertEquals ( 88, r.next ().getInt ( 1 ) );
        else
            Assertions.fail ( "Should not have next" );
        
        Assertions.assertFalse ( r.hasNext () );

        EasyMock.verify ( md, rs );
    }
    
    @Test
    public void testGetInt_2_Rows_1_Column_Loop() throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );
        EasyMock.expect ( rs.isBeforeFirst () ).andReturn ( false );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 77 );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );
        EasyMock.expect ( rs.isBeforeFirst () ).andReturn ( false );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 88 );

        EasyMock.expect ( rs.next () ).andReturn ( false );
        
        EasyMock.replay ( md, rs );
                
        int[] expect = { 77, 88 };
        int pos = 0;
        
        for ( ResultSet r : new Results ( rs ) )
            Assertions.assertEquals ( expect[ pos++ ], r.getInt ( 1 ) );

        EasyMock.verify ( md, rs );
    }
    
    @Test
    public void testGetInt_2_Rows_2_Columns_Iterator() throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );
        EasyMock.expect ( rs.isBeforeFirst () ).andReturn ( false );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 77 );
        EasyMock.expect ( rs.getInt ( 2 ) ).andReturn ( 88 );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );        
        EasyMock.expect ( rs.isBeforeFirst () ).andReturn ( false );

        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 99 );
        EasyMock.expect ( rs.getInt ( 2 ) ).andReturn ( 22 );
        
        EasyMock.expect ( rs.next () ).andReturn ( false );
        
        EasyMock.replay ( md, rs );
        
        Results r = new Results ( rs );
        
        if ( r.hasNext () )
        {
            ResultSet row = r.next ();
            
            Assertions.assertEquals ( 77, row.getInt ( 1 ) );
            Assertions.assertEquals ( 88, row.getInt ( 2 ) );
        }
        else
            Assertions.fail ( "Should Have Had Next" );
        
        if ( r.hasNext () )
        {
            ResultSet row = r.next ();
            
            Assertions.assertEquals ( 99, row.getInt ( 1 ) );
            Assertions.assertEquals ( 22, row.getInt ( 2 ) );
        }

        Assertions.assertFalse ( r.hasNext () );

        EasyMock.verify ( md, rs );
   }
    
    @Test
    public void testGetInt_2_Rows_2_Columns_Loop() throws SQLException
    {
        ResultSetMetaData md = EasyMock.createMock ( ResultSetMetaData.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );
        EasyMock.expect ( rs.isBeforeFirst () ).andReturn ( false );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 77 );
        EasyMock.expect ( rs.getInt ( 2 ) ).andReturn ( 88 );
        
        EasyMock.expect ( rs.next () ).andReturn ( true );        
        EasyMock.expect ( rs.isBeforeFirst () ).andReturn ( false );
        EasyMock.expect ( rs.getInt ( 1 ) ).andReturn ( 99 );
        EasyMock.expect ( rs.getInt ( 2 ) ).andReturn ( 22 );
        
        EasyMock.expect ( rs.next () ).andReturn ( false );
        
        EasyMock.replay ( md, rs );
        
        int[] expect = { 77, 88, 99, 22 };
        int pos = 0;
                
        for ( ResultSet r : new Results ( rs ) )
        {
            Assertions.assertEquals ( expect[ pos++ ], r.getInt ( 1 ) );
            Assertions.assertEquals ( expect[ pos++ ], r.getInt ( 2 ) );
        }

        EasyMock.verify ( md, rs );
   }
}
