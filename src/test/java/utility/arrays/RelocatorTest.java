package utility.arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RelocatorTest
{
    public List<Integer> list;
    public int[] iarray;
    public String[] sarray;
    
    @Before
    public void before()
    {
        list = new ArrayList<> ( Arrays.asList ( 0, 1, 2, 3, 4, 5, 6 ) );
        iarray = new int[] { 0, 1, 2, 3, 4, 5, 6 };
        sarray = new String[] { "A", "B", "C", "D", "E", "F", "G" };
    }
    
    @Test
    public void testList_First_In_Place ()
    {
        Relocator.relocate ( list, 0, 0 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 2, 3, 4, 5, 6 ) ) );
    }
    
    @Test
    public void testList_First_Down_One ()
    {
        Relocator.relocate ( list, 0, 1 );
        
        assertThat ( list, is ( Arrays.asList ( 1, 0, 2, 3, 4, 5, 6 ) ) );
    }
    
    @Test
    public void testList_First_Down_Two ()
    {
        Relocator.relocate ( list, 0, 2 );
        
        assertThat ( list, is ( Arrays.asList ( 1, 2, 0, 3, 4, 5, 6 ) ) );
    }
    
    @Test
    public void testList_First_to_Last ()
    {
        Relocator.relocate ( list, 0, 6 );
        
        assertThat ( list, is ( Arrays.asList ( 1, 2, 3, 4, 5, 6, 0 ) ) );
    }
    
    @Test
    public void testList_Last_To_First ()
    {
        Relocator.relocate ( list, 6, 0 );
        
        assertThat ( list, is ( Arrays.asList ( 6, 0, 1, 2, 3, 4, 5 ) ) );
    }
    
    @Test
    public void testList_Last_Up_Two ()
    {
        Relocator.relocate ( list, 6, 4 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 2, 3, 6, 4, 5 ) ) );
    }
    
    @Test
    public void testList_Last_Up_One ()
    {
        Relocator.relocate ( list, 6, 5 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 2, 3, 4, 6, 5 ) ) );
    }
    
    @Test
    public void testList_Last_In_Place ()
    {
        Relocator.relocate ( list, 6, 6 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 2, 3, 4, 5, 6 ) ) );
    }
    
    @Test
    public void testList_Middle_To_First ()
    {
        Relocator.relocate ( list, 3, 0 );
        
        assertThat ( list, is ( Arrays.asList ( 3, 0, 1, 2, 4, 5, 6 ) ) );
    }
    
    @Test
    public void testList_Middle_Up_Two ()
    {
        Relocator.relocate ( list, 3, 1 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 3, 1, 2, 4, 5, 6 ) ) );
    }
    
    @Test
    public void testList_Middle_In_Place ()
    {
        Relocator.relocate ( list, 3, 3 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 2, 3, 4, 5, 6 ) ) );
    }
    
    @Test
    public void testList_Middle_Up_One ()
    {
        Relocator.relocate ( list, 3, 2 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 3, 2, 4, 5, 6 ) ) );
    }
    
    @Test
    public void testList_Middle_Down_One ()
    {
        Relocator.relocate ( list, 3, 4 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 2, 4, 3, 5, 6 ) ) );
    }
    
    @Test
    public void testList_Middle_Down_Two ()
    {
        Relocator.relocate ( list, 3, 5 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 2, 4, 5, 3, 6 ) ) );
    }
    
    @Test
    public void testList_Middle_To_Last ()
    {
        Relocator.relocate ( list, 3, 6 );
        
        assertThat ( list, is ( Arrays.asList ( 0, 1, 2, 4, 5, 6, 3 ) ) );
    }
    
    @Test
    public void testStringArray_First_In_Place ()
    {
        Relocator.relocate ( sarray, 0, 0 );
        
        assertArrayEquals ( new String[] { "A", "B", "C", "D", "E", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_First_Down_One ()
    {
        Relocator.relocate ( sarray, 0, 1 );
        
        assertArrayEquals ( new String[] { "B", "A", "C", "D", "E", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_First_Down_Two ()
    {
        Relocator.relocate ( sarray, 0, 2 );
        
        assertArrayEquals ( new String[] { "B", "C", "A", "D", "E", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_First_to_Last ()
    {
        Relocator.relocate ( sarray, 0, 6 );
        
        assertArrayEquals ( new String[] { "B", "C", "D", "E", "F", "G", "A" }, sarray );
    }
    
    @Test
    public void testStringArray_Last_To_First ()
    {
        Relocator.relocate ( sarray, 6, 0 );
        
        assertArrayEquals ( new String[] { "G", "A", "B", "C", "D", "E", "F" }, sarray );
    }
    
    @Test
    public void testStringArray_Last_Up_Two ()
    {
        Relocator.relocate ( sarray, 6, 4 );
        
        assertArrayEquals ( new String[] { "A", "B", "C", "D", "G", "E", "F" }, sarray );
    }
    
    @Test
    public void testStringArray_Last_Up_One ()
    {
        Relocator.relocate ( sarray, 6, 5 );
        
        assertArrayEquals ( new String[] { "A", "B", "C", "D", "E", "G", "F" }, sarray );
    }
    
    @Test
    public void testStringArray_Last_In_Place ()
    {
        Relocator.relocate ( sarray, 6, 6 );
        
        assertArrayEquals ( new String[] { "A", "B", "C", "D", "E", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_Middle_To_First ()
    {
        Relocator.relocate ( sarray, 3, 0 );
        
        assertArrayEquals ( new String[] { "D", "A", "B", "C", "E", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_Middle_Up_Two ()
    {
        Relocator.relocate ( sarray, 3, 1 );
        
        assertArrayEquals ( new String[] { "A", "D", "B", "C", "E", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_Middle_In_Place ()
    {
        Relocator.relocate ( sarray, 3, 3 );
        
        assertArrayEquals ( new String[] { "A", "B", "C", "D", "E", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_Middle_Up_One ()
    {
        Relocator.relocate ( sarray, 3, 2 );
        
        assertArrayEquals ( new String[] { "A", "B", "D", "C", "E", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_Middle_Down_One ()
    {
        Relocator.relocate ( sarray, 3, 4 );
        
        assertArrayEquals ( new String[] { "A", "B", "C", "E", "D", "F", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_Middle_Down_Two ()
    {
        Relocator.relocate ( sarray, 3, 5 );
        
        assertArrayEquals ( new String[] { "A", "B", "C", "E", "F", "D", "G" }, sarray );
    }
    
    @Test
    public void testStringArray_Middle_To_Last ()
    {
        Relocator.relocate ( sarray, 3, 6 );
        
        assertArrayEquals ( new String[] { "A", "B", "C", "E", "F", "G", "D" }, sarray );
    }
}
