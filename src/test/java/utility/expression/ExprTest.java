package utility.expression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExprTest
{
	@Test
	public void testEval() 
	{
		Expr e;
		
		assertEquals ( 3, new Expr ( "3" ).eval(), 0.00001 );
		assertEquals ( 3, new Expr ( "\t\t3   " ).eval(), 0.00001 );

		assertEquals ( 12, new Expr ( " 2 * 6" ).eval(), 0.00001 );
		assertEquals ( 888, new Expr ( "345+543" ).eval(), 0.00001 );
		
		assertEquals ( 2, new Expr ( "(1+1)" ).eval(), 0.00001 );
		assertEquals ( 6, new Expr ( "3 * ( 1 + 1 )" ).eval(), 0.00001 );
		assertEquals ( 6, new Expr ( " ( 1 + 1 ) * 3 " ).eval(), 0.00001 );
		
		assertEquals ( 77, new Expr ( "( 77 )" ).eval(), 0.00001 );
		
		assertEquals ( 29, new Expr ( "( 4*( 1+1 ) ) + 3 * ( 7 + 0 )" ).eval(), 0.00001 );

		// First Operation is Addition
		
		assertEquals ( 10, new Expr ( "1+2+3+4" ).eval(), 0.00001 );
		assertEquals ( 2, new Expr ( "1+2+3-4" ).eval(), 0.00001 );
		assertEquals ( 15, new Expr ( "1+2+3*4" ).eval(), 0.00001 );
		assertEquals ( 3.75, new Expr ( "1+2+3/4" ).eval(), 0.00001 );

		assertEquals ( 4, new Expr ( "1+2-3+4" ).eval(), 0.00001 );
		assertEquals ( -4, new Expr ( "1+2-3-4" ).eval(), 0.00001 );
		assertEquals ( -9, new Expr ( "1+2-3*4" ).eval(), 0.00001 );
		assertEquals ( 2.25, new Expr ( "1+2-3/4" ).eval(), 0.00001 );

		assertEquals ( 11, new Expr ( "1+2*3+4" ).eval(), 0.00001 );
		assertEquals ( 3, new Expr ( "1+2*3-4" ).eval(), 0.00001 );
		assertEquals ( 25, new Expr ( "1+2*3*4" ).eval(), 0.00001 );
		assertEquals ( 2.5, new Expr ( "1+2*3/4" ).eval(), 0.00001 );

		assertEquals ( 5.66666, new Expr ( "1+2/3+4" ).eval(), 0.00001 );
		assertEquals ( -2.33333, new Expr ( "1+2/3-4" ).eval(), 0.00001 );
		assertEquals ( 3.66666, new Expr ( "1+2/3*4" ).eval(), 0.00001 );
		assertEquals ( 1.16666, new Expr ( "1+2/3/4" ).eval(), 0.00001 );

		// First Operation is Subtraction
		
		assertEquals ( 6, new Expr ( "1-2+3+4" ).eval(), 0.00001 );
		assertEquals ( -2, new Expr ( "1-2+3-4" ).eval(), 0.00001 );
		assertEquals ( 11, new Expr ( "1-2+3*4" ).eval(), 0.00001 );
		assertEquals ( -0.25, new Expr ( "1-2+3/4" ).eval(), 0.00001 );

		assertEquals ( 0, new Expr ( "1-2-3+4" ).eval(), 0.00001 );
		assertEquals ( -8, new Expr ( "1-2-3-4" ).eval(), 0.00001 );
		assertEquals ( -13, new Expr ( "1-2-3*4" ).eval(), 0.00001 );
		assertEquals ( -1.75, new Expr ( "1-2-3/4" ).eval(), 0.00001 );

		assertEquals ( -1, new Expr ( "1-2*3+4" ).eval(), 0.00001 );
		assertEquals ( -9, new Expr ( "1-2*3-4" ).eval(), 0.00001 );
		assertEquals ( -23, new Expr ( "1-2*3*4" ).eval(), 0.00001 );
		assertEquals ( -0.5, new Expr ( "1-2*3/4" ).eval(), 0.00001 );

		assertEquals ( 4.33333, new Expr ( "1-2/3+4" ).eval(), 0.00001 );
		assertEquals ( -3.66666, new Expr ( "1-2/3-4" ).eval(), 0.00001 );
		assertEquals ( -1.66666, new Expr ( "1-2/3*4" ).eval(), 0.00001 );
		assertEquals ( 0.83333, new Expr ( "1-2/3/4" ).eval(), 0.00001 );

		// First Operation is Multiplication
		
		assertEquals ( 9, new Expr ( "1*2+3+4" ).eval(), 0.00001 );
		assertEquals ( 1, new Expr ( "1*2+3-4" ).eval(), 0.00001 );
		assertEquals ( 14, new Expr ( "1*2+3*4" ).eval(), 0.00001 );
		assertEquals ( 2.75, new Expr ( "1*2+3/4" ).eval(), 0.00001 );

		assertEquals ( 3, new Expr ( "1*2-3+4" ).eval(), 0.00001 );
		assertEquals ( -5, new Expr ( "1*2-3-4" ).eval(), 0.00001 );
		assertEquals ( -10, new Expr ( "1*2-3*4" ).eval(), 0.00001 );
		assertEquals ( 1.25, new Expr ( "1*2-3/4" ).eval(), 0.00001 );

		assertEquals ( 10, new Expr ( "1*2*3+4" ).eval(), 0.00001 );
		assertEquals ( 2, new Expr ( "1*2*3-4" ).eval(), 0.00001 );
		assertEquals ( 24, new Expr ( "1*2*3*4" ).eval(), 0.00001 );
		assertEquals ( 1.5, new Expr ( "1*2*3/4" ).eval(), 0.00001 );

		assertEquals ( 4.66666, new Expr ( "1*2/3+4" ).eval(), 0.00001 );
		assertEquals ( -3.33333, new Expr ( "1*2/3-4" ).eval(), 0.00001 );
		assertEquals ( 2.66666, new Expr ( "1*2/3*4" ).eval(), 0.00001 );
		assertEquals ( 0.16666, new Expr ( "1*2/3/4" ).eval(), 0.00001 );

		// First Operation is Division
		
		assertEquals ( 7.5, new Expr ( "1/2+3+4" ).eval(), 0.00001 );
		assertEquals ( -0.5, new Expr ( "1/2+3-4" ).eval(), 0.00001 );
		assertEquals ( 12.5, new Expr ( "1/2+3*4" ).eval(), 0.00001 );
		assertEquals ( 1.25, new Expr ( "1/2+3/4" ).eval(), 0.00001 );

		assertEquals ( 1.5, new Expr ( "1/2-3+4" ).eval(), 0.00001 );
		assertEquals ( -6.5, new Expr ( "1/2-3-4" ).eval(), 0.00001 );
		assertEquals ( -11.5, new Expr ( "1/2-3*4" ).eval(), 0.00001 );
		assertEquals ( -0.25, new Expr ( "1/2-3/4" ).eval(), 0.00001 );

		assertEquals ( 5.5, new Expr ( "1/2*3+4" ).eval(), 0.00001 );
		assertEquals ( -2.5, new Expr ( "1/2*3-4" ).eval(), 0.00001 );
		assertEquals ( 6, new Expr ( "1/2*3*4" ).eval(), 0.00001 );
		assertEquals ( 0.375, new Expr ( "1/2*3/4" ).eval(), 0.00001 );

		assertEquals ( 4.16666, new Expr ( "1/2/3+4" ).eval(), 0.00001 );
		assertEquals ( -3.83333, new Expr ( "1/2/3-4" ).eval(), 0.00001 );
		assertEquals ( 0.66666, new Expr ( "1/2/3*4" ).eval(), 0.00001 );
		assertEquals ( 0.04166, new Expr ( "1/2/3/4" ).eval(), 0.00001 );
		
		assertEquals ( 24, new Expr ( "4!" ).eval(), 0.00001 );
		assertEquals ( 72, new Expr ( "3*4!" ).eval(), 0.00001 );
		assertEquals ( 72, new Expr ( "4!*3" ).eval(), 0.00001 );
		
		assertEquals ( -32, new Expr ( "-32" ).eval(), 0.00001 );
		assertEquals ( -26, new Expr ( "-32+6" ).eval(), 0.00001 );
		assertEquals ( 68, new Expr ( "100+-32" ).eval(), 0.00001 );
		assertEquals ( -15, new Expr ( "(1+2)*-(2+3)" ).eval(), 0.00001 );
		
		e = new Expr ( "3*x" );
		assertEquals ( 3, e.eval ( 1 ), 0.00001 );
		assertEquals ( 6, e.eval ( 2 ), 0.00001 );
		assertEquals ( 9, e.eval ( 3 ), 0.00001 );
		
		e = new Expr ( "y+3*x" );
		assertEquals ( 4, e.eval ( 1, 1 ), 0.00001 );
		assertEquals ( 5, e.eval ( 1, 2 ), 0.00001 );
		assertEquals ( 7, e.eval ( 2, 1 ), 0.00001 );
		assertEquals ( 8, e.eval ( 2, 2 ), 0.00001 );
		
		e = new Expr ( "x^y" );
		assertEquals ( 16, e.eval ( 2, 4 ), 0.00001 );
		assertEquals ( 27, e.eval ( 3, 3 ), 0.00001 );
		
		e = new Expr ( "x^y^z" );
		assertEquals ( 262144, e.eval ( 4, 3, 2 ), 0.0000001 );
		assertEquals ( 43046721, e.eval ( 3, 4, 2 ), 0.0000001 );

		assertEquals ( 2, new Expr ( "sqrt(4)" ).eval(), 0.00001 );
		assertEquals ( 3, new Expr ( "sqrt ( 9 )" ).eval(), 0.00001 );
		assertEquals ( 17, new Expr ( "3*sqrt ( 25 )+2" ).eval(), 0.00001 );

		assertEquals ( 0, new Expr ( "sin(0)" ).eval(), 0.000001 );
		assertEquals ( .5, new Expr ( "sin(PI/6)" ).eval(), 0.000001 );

		assertEquals ( 1, new Expr ( "cos(0)" ).eval(), 0.000001 );
		assertEquals ( .5, new Expr ( "cos(PI/3)" ).eval(), 0.000001 );
		
		assertEquals ( 3, new Expr ( "log ( 1000 )" ).eval(), 0.000001 );
		assertEquals ( 5.5, new Expr ( "4+log ( 1000 ) * sin(PI/6)" ).eval(), 0.000001 );
		
		//
		// Errors
		//
		
		//e = new Expr ( "fred" );
	}
}
