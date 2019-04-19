package utility.sql;


import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.matches;

public class DbScriptTest
{    
    @Test
    public void testReadable () throws SQLException, IOException
    {
        Connection conn = EasyMock.createMock ( Connection.class );
        PreparedStatement stmt = EasyMock.createMock ( PreparedStatement.class );
        ResultSet rs = EasyMock.createMock ( ResultSet.class );
        
        EasyMock.expect ( conn.prepareStatement ( matches ( "^block\\s+begin\\s+stmt;\\s+end$" ) ) ).andReturn ( stmt );
        EasyMock.expect ( stmt.execute () ).andReturn ( false );
        
        EasyMock.expect ( conn.prepareStatement ( matches ( "^stmt$" ) ) ).andReturn ( stmt );
        EasyMock.expect ( stmt.execute () ).andReturn ( true );
        EasyMock.expect ( stmt.getResultSet () ).andReturn ( rs );
        EasyMock.expect ( rs.next () ).andReturn ( true );
        
        EasyMock.replay ( conn, stmt, rs );
        
        Results results = new DbScript ( conn ).run ( new StringReader ( "\n\n;\n\n;;\n\nblock\nbegin\n\tstmt;\nend;\n\nstmt;\n\n" ) );
        
        Assertions.assertEquals ( rs, results.single () );
        
        EasyMock.verify ( conn, stmt, rs );
    }
}
