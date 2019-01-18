package utility.sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DbScript
{
    private static final Pattern BEGIN_PATTERN = Pattern.compile ( "\\bbegin\\b", Pattern.CASE_INSENSITIVE );
    private static final Pattern END_PATTERN = Pattern.compile ( "\\bend\\b\\s*;", Pattern.CASE_INSENSITIVE );

    private Connection db;
    
    public DbScript ( Connection db )
    {
        this.db = db;
    }
    
    public Results run ( Path path ) throws IOException, SQLException
    {
        Scanner scanner = new Scanner ( path );
        Results results = run ( scanner );
        scanner.close ();
        
        return results;
    }

    public Results run ( File file ) throws FileNotFoundException, SQLException
    {
        Scanner scanner = new Scanner ( file );
        Results results = run ( scanner );
        scanner.close ();
        
        return results;
    }

    public Results run ( URL url ) throws IOException, SQLException
    {
        return run ( url.openStream () );
    }
    
    public Results run ( URI uri ) throws MalformedURLException, IOException, SQLException
    {
        return run ( uri.toURL () );
    }
    
    public Results run ( InputStream is ) throws SQLException
    {
        Scanner scanner = new Scanner ( is );
        Results results = run ( scanner );
        scanner.close ();
        
        return results;
    }

    public Results run ( Readable readable ) throws SQLException
    {
        Scanner scanner = new Scanner ( readable );
        Results results = run ( scanner );
        scanner.close ();
        
        return results;
    }

    public Results run ( Scanner scanner ) throws SQLException
    {
        scanner.useDelimiter ( ";" );
                
        Results results = null;
        
        while ( scanner.hasNext () )
        {
            String sql = scanner.next ();
            
            // If the statement contains a BEGIN, then we need to find the END.
            if ( BEGIN_PATTERN.matcher ( sql ).find () )
            {
                scanner.useDelimiter ( END_PATTERN );
                sql = sql + scanner.next () + "end";
                
                scanner.useDelimiter ( ";" );
                
                // The delimiter "end;" was left by the last call, so we have to discard it.
                scanner.next ();
            }

            sql = sql.trim();
            
            if ( !sql.isEmpty () )
                results = new Sql ( db, sql ).go ();
        }
        
        return results;
    }
}
