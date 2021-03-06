package utility.collections;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListRearrangerTest
{
    public List<Integer> list;
    
    @BeforeEach
    public void beforeEach()
    {
        list = Arrays.asList ( 1, 2, 3, 4, 5, 6, 7 );
    }
    
    @Test
    public void testReverse ()
    {        
        list.sort ( ListRearranger.reverse ( list ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 7, 6, 5, 4, 3, 2, 1 }, list.toArray () );
    }
    
    @Test
    public void testFistToLast ()
    {        
        list.sort ( new ListRearranger<> ( list, 6, 0, 1, 2, 3, 4, 5 ) );
                
        Assertions.assertArrayEquals ( new Integer[] { 2, 3, 4, 5, 6, 7, 1 }, list.toArray () );
    }
    
    @Test
    public void testSwapFirstTwo ()
    {
        list.sort ( new ListRearranger<> ( list, 1, 0, 2, 3, 4, 5, 6 ) );
                
        Assertions.assertArrayEquals ( new Integer[] { 2, 1, 3, 4, 5, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testUnchanged ()
    {
        list.sort ( new ListRearranger<> ( list, 0, 1, 2, 3, 4, 5, 6 ) );
                
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testRotateZero ()
    {
        list.sort ( ListRearranger.rotate ( list, 0 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testRotateOne ()
    {
        list.sort ( ListRearranger.rotate ( list, 1 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 2, 3, 4, 5, 6, 7, 1 }, list.toArray () );
    }
    
    @Test
    public void testRotateTwo ()
    {
        list.sort ( ListRearranger.rotate ( list, 2 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 3, 4, 5, 6, 7, 1, 2 }, list.toArray () );
    }
    
    @Test
    public void testRotateMinusOne ()
    {
        list.sort ( ListRearranger.rotate ( list, -1 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 7, 1, 2, 3, 4, 5, 6 }, list.toArray () );
    }
    
    @Test
    public void testRotateMinusTwo ()
    {
        list.sort ( ListRearranger.rotate ( list, -2 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 6, 7, 1, 2, 3, 4, 5 }, list.toArray () );
    }
    
    @Test
    public void testMove_First_In_Place ()
    {
        list.sort ( ListRearranger.move ( list, 0, 0 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testMove_First_Down_One ()
    {
        list.sort ( ListRearranger.move ( list, 0, 1 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 2, 1, 3, 4, 5, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testMove_First_Down_Two ()
    {
        list.sort ( ListRearranger.move ( list, 0, 2 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 2, 3, 1, 4, 5, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testMove_First_To_Last ()
    {
        list.sort ( ListRearranger.move ( list, 0, 6 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 2, 3, 4, 5, 6, 7, 1 }, list.toArray () );
    }
    
    @Test
    public void testMove_Last_To_First ()
    {
        list.sort ( ListRearranger.move ( list, 6, 0 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 7, 1, 2, 3, 4, 5, 6 }, list.toArray () );
    }
    @Test
    public void testMove_Last_Up_Two ()
    {
        list.sort ( ListRearranger.move ( list, 6, 4 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 7, 5, 6 }, list.toArray () );
    }
    @Test
    public void testMove_Last_Up_One ()
    {
        list.sort ( ListRearranger.move ( list, 6, 5 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 7, 6 }, list.toArray () );
    }
    @Test
    public void testMove_Last_In_Place ()
    {
        list.sort ( ListRearranger.move ( list, 6, 6 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testMove_Middle_To_First ()
    {
        list.sort ( ListRearranger.move ( list, 3, 0 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 4, 1, 2, 3, 5, 6, 7 }, list.toArray () );
    }
    @Test
    public void testMove_Middle_Up_Two ()
    {
        list.sort ( ListRearranger.move ( list, 3, 1 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 4, 2, 3, 5, 6, 7 }, list.toArray () );
    }
    @Test
    public void testMove_Middle_Up_One ()
    {
        list.sort ( ListRearranger.move ( list, 3, 2 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 4, 3, 5, 6, 7 }, list.toArray () );
    }
    @Test
    public void testMove_Middle_In_Place ()
    {
        list.sort ( ListRearranger.move ( list, 3, 3 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testMove_Middle_Down_One ()
    {
        list.sort ( ListRearranger.move ( list, 3, 4 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 5, 4, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testMove_Middle_Down_Two ()
    {
        list.sort ( ListRearranger.move ( list, 3, 5 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 5, 6, 4, 7 }, list.toArray () );
    }
    
    @Test
    public void testMove_Middle_To_Last ()
    {
        list.sort ( ListRearranger.move ( list, 3, 6 ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 5, 6, 7, 4 }, list.toArray () );
    }
    
    @Test
    public void testReverse_Middle ()
    {
        List<Integer> middle = list.subList ( 2, 5 );
        middle.sort ( ListRearranger.reverse ( middle ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 5, 4, 3, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testReverse_Middle_Bigger ()
    {
        List<Integer> middle = list.subList ( 1, 5 );
        middle.sort ( ListRearranger.reverse ( middle ) );
                        
        Assertions.assertArrayEquals ( new Integer[] { 1, 5, 4, 3, 2, 6, 7 }, list.toArray () );
    }
    
    @Test
    public void testSwap_First_In_Place()
    {
        list.sort ( ListRearranger.swap ( list, 0, 0 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, list.toArray () );

    }
    
    @Test
    public void testSwap_First_Middle()
    {
        list.sort ( ListRearranger.swap ( list, 0, 3 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 4, 2, 3, 1, 5, 6, 7 }, list.toArray () );

    }
    
    @Test
    public void testSwap_First_Last()
    {
        list.sort ( ListRearranger.swap ( list, 0, 6 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 7, 2, 3, 4, 5, 6, 1 }, list.toArray () );

    }
    
    @Test
    public void testSwap_Last_In_Place()
    {
        list.sort ( ListRearranger.swap ( list, 6, 6 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, list.toArray () );

    }
    
    @Test
    public void testSwap_Last_Middle()
    {
        list.sort ( ListRearranger.swap ( list, 6, 3 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 7, 5, 6, 4 }, list.toArray () );

    }
    
    @Test
    public void testSwap_Last_First()
    {
        list.sort ( ListRearranger.swap ( list, 6, 0 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 7, 2, 3, 4, 5, 6, 1 }, list.toArray () );

    }
    
    @Test
    public void testSwap_Middle_In_Place()
    {
        list.sort ( ListRearranger.swap ( list, 3, 3 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, list.toArray () );

    }
    
    @Test
    public void testSwap_Middle_First()
    {
        list.sort ( ListRearranger.swap ( list, 3, 0 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 4, 2, 3, 1, 5, 6, 7 }, list.toArray () );

    }
    
    @Test
    public void testSwap_Middle_Last()
    {
        list.sort ( ListRearranger.swap ( list, 3, 6 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 3, 7, 5, 6, 4 }, list.toArray () );

    }
    
    @Test
    public void testSwap_Middle_Positive()
    {
        list.sort ( ListRearranger.swap ( list, 2, 4 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 1, 2, 5, 4, 3, 6, 7 }, list.toArray () );

    }
    
    @Test
    public void testSwap_Middle_Negative()
    {
        list.sort ( ListRearranger.swap ( list, 5, 1 ) );
        
        Assertions.assertArrayEquals ( new Integer[] { 1, 6, 3, 4, 5, 2, 7 }, list.toArray () );

    }
}
