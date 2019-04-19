package utility.stopwatch;


import java.util.function.LongSupplier;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StopWatchTest
{
    public static final LongSupplier mockTimeSupplier = EasyMock.createMock ( LongSupplier.class );
    
    @BeforeEach
    public void beforeEach()
    {
        EasyMock.reset ( mockTimeSupplier );
    }
    
    @AfterEach
    public void afterEach()
    {
        EasyMock.verify ( mockTimeSupplier );
    }
    
    @Test
    public void testZero()
    {
        EasyMock.replay ( mockTimeSupplier );
        
        StopWatch stopWatch = new StopWatch ( mockTimeSupplier );

        Assertions.assertEquals ( Long.MIN_VALUE, stopWatch.start () );
        Assertions.assertEquals ( Long.MAX_VALUE, stopWatch.end () );
        Assertions.assertEquals ( Long.MIN_VALUE, stopWatch.duration () );
    }
    
    @Test
    public void testOne()
    {
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 1 );
        
        EasyMock.replay ( mockTimeSupplier );
        
        StopWatch stopWatch = new StopWatch ( mockTimeSupplier );
        stopWatch.mark ();
        
        Assertions.assertEquals ( 1, stopWatch.start () );
        Assertions.assertEquals ( 1, stopWatch.end () );
        Assertions.assertEquals ( 0, stopWatch.duration () );
    }
    
    @Test
    public void testTwo()
    {
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 1 );
        
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 3 );
        
        EasyMock.replay ( mockTimeSupplier );
        
        StopWatch stopWatch = new StopWatch ( mockTimeSupplier );
        stopWatch.mark ();
        stopWatch.mark ();
        
        Assertions.assertEquals ( 1, stopWatch.start () );
        Assertions.assertEquals ( 3, stopWatch.end () );
        Assertions.assertEquals ( 2, stopWatch.duration () );
    }
    
    @Test
    public void testThree()
    {
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 1 );
        
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 3 );
        
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 8 );
        
        EasyMock.replay ( mockTimeSupplier );
        
        StopWatch stopWatch = new StopWatch ( mockTimeSupplier );
        stopWatch.mark ();
        stopWatch.mark ();
        stopWatch.mark ();
        
        Assertions.assertEquals ( 1, stopWatch.start () );
        Assertions.assertEquals ( 8, stopWatch.end () );
        
        Assertions.assertEquals ( 7, stopWatch.duration () );
        Assertions.assertEquals ( 0, stopWatch.duration ( 0 ) );
        Assertions.assertEquals ( 2, stopWatch.duration ( 1 ) );
        Assertions.assertEquals ( 5, stopWatch.duration ( 1, 2 ) );
    }
    
    @Test
    public void testErrors()
    {
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 1 );
        
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 3 );
        
        EasyMock.replay ( mockTimeSupplier );
        
        StopWatch stopWatch = new StopWatch ( mockTimeSupplier );
        
        Assertions.assertEquals ( Long.MIN_VALUE, stopWatch.duration () );
        Assertions.assertEquals ( Long.MIN_VALUE, stopWatch.duration ( -1 ) );
        Assertions.assertEquals ( Long.MIN_VALUE, stopWatch.duration ( 0, 1 ) );
        
        stopWatch.mark ();
        
        Assertions.assertEquals ( Long.MIN_VALUE, stopWatch.duration ( -1 ) );
        Assertions.assertEquals ( Long.MAX_VALUE, stopWatch.duration ( 0, 7 ) );
        
        stopWatch.mark ();
        
        Assertions.assertEquals ( Long.MIN_VALUE, stopWatch.duration ( -1 ) );
        Assertions.assertEquals ( Long.MAX_VALUE, stopWatch.duration ( 0, 7 ) );
    }
    
    @Test
    public void testRun()
    {
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 77 );
        
        mockTimeSupplier.getAsLong ();
        EasyMock.expectLastCall ().andReturn ( 83 );
        
        EasyMock.replay ( mockTimeSupplier );
        
        StopWatch stopWatch = new StopWatch ( mockTimeSupplier );
        
        Assertions.assertEquals ( 6, stopWatch.run ( ( ) -> {} ) );
    }
}
