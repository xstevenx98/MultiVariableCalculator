/*
 * @author Steven Imas
 * 
 * the MultiBaseCalculatorState runs the methods used in the
 * calculator.  It contains the string currentValue to keep track
 * of and manipulate the value of the result via subtraction, multiplication,
 * addition, or division.  The base is set to a default value in base10 to keep track of the value
 * of the numbers in regular form.  All calculations regarding
 * converting between bases must go through its base 10 form first.
 *  
 */

public class MultiBaseCalculatorState
{
	/*** Class Constants ***/
	
	private int    DEFAULT_BASE_NUMBER = 10;
	private String DEFAULT_VALUE       =  "0";
	
	/*** Class Variables ***/
	
	private int    base;
	private String currentValue;

	/*** Constructors ***/
	/*
	 * @param base   		keeps track of the base the JSlider is currently in
	 * @param currentValue  keeps track of the number stored in the result textField.
	 * 						This number can be manipulated by moving the slider between bases.
	 * 
	 * @see 				currentValue
	 */
	
	public MultiBaseCalculatorState()
	{	
		this.base         = DEFAULT_BASE_NUMBER;
		this.currentValue = DEFAULT_VALUE;
	}

	public MultiBaseCalculatorState( int base )
	{	
		this.base         = base;
		this.currentValue = DEFAULT_VALUE;
	}
	
	/*** Accessor methods ***/
	
	public int getBase()
	{
		return this.base;
	}

	public String getCurrentValue()
	{
		return this.currentValue;
	}	
	
	public String toString()
	{
		return "Base: " + this.getBase() + 
			   " Current Value: " + this.getCurrentValue();
	}

	/*** Mutator methods***/
	
	public void setBase( int newBase )
	{		
		/*** convert current value to new base ***/
		
		//this.currentValue = DEFAULT_VALUE;
		
			
		/*** Set calculator to new base ***/
		
	    this.base = newBase;
	    
	}
	
	public void setCurrentValue( String value )
	{
		this.currentValue = value;
	}	
			
	public void resetCurrentValue()
	{
		this.currentValue = DEFAULT_VALUE;
	}	
	
	public String add( String strOperand )
	{
		/*** Local Variables ***/
		
		int intOperand  = MultiBaseCalculatorState.convertToBase10( base, strOperand );
		int valueBase10 = MultiBaseCalculatorState.convertToBase10( base, currentValue );
				
		valueBase10 = valueBase10 + intOperand;
		
		currentValue = MultiBaseCalculatorState.convertFromBase10( valueBase10, base );
		
		return currentValue;
	}
	
	public String subtract( String strOperand )
	{
		/*** Local Variables ***/
		
		int intOperand  = MultiBaseCalculatorState.convertToBase10( base, strOperand );
		int valueBase10 = MultiBaseCalculatorState.convertToBase10( base, currentValue );
				
		valueBase10 = valueBase10 - intOperand;
		
		currentValue = MultiBaseCalculatorState.convertFromBase10( valueBase10, base );
		
		return currentValue;
	}
	
	public String multiply( String strOperand )
	{
		/*** Local Variables ***/
		
		int intOperand  = MultiBaseCalculatorState.convertToBase10( base, strOperand );
		int valueBase10 = MultiBaseCalculatorState.convertToBase10( base, currentValue );
				
		valueBase10 = valueBase10 * intOperand;
		
		currentValue = MultiBaseCalculatorState.convertFromBase10( valueBase10, base );
		
		return currentValue;
	}
	
	public String divide( String strOperand )
	{
		/*** Local Variables ***/
		
		int intOperand  = MultiBaseCalculatorState.convertToBase10( base, strOperand );
		int valueBase10 = MultiBaseCalculatorState.convertToBase10( base, currentValue );
				
		valueBase10 = valueBase10 / intOperand;
		
		currentValue = MultiBaseCalculatorState.convertFromBase10( valueBase10, base );
		
		return currentValue;
	}
	
	public static int convertToBase10( int fromBase, String strValue )  // 160 base 8 --> base 10
	{
		/*** Local Variables ***/  
		
		char character;
		int valueBase10 = 0;
		int exponent = 0;
		int digit = 0;
		
		for ( int index = strValue.length()-1 ; index >= 0; index-- )
		{
		   character = strValue.charAt( index );
		   
		   if ( character >= '0' && character <= '9' )
              digit = Integer.parseInt( "" + character );
		   else
			  digit = (int) (character -'A') + 10;
			
		   valueBase10 += ( digit * Math.pow( fromBase, exponent ) );
		   
		   //System.out.println( "digit: " + digit + " exponent: " + exponent + "Base10: " + valueBase10 );
		
		   exponent++;
		}
		
		return valueBase10;
	}
	
	public static String convertFromBase10( int valueBase10, int newBase )  // 16 (Base 10) --> A (base 16)
	{
		/*** Local Variables ***/
			
		String newBaseCharacter[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				                     "A", "B", "C", "D", "E", "F" };
		
		int remainder = 0;
		int exponent = 10;
		
		String newValue = "";
		
		int baseNumber = 0;
		
        /*** Convert to new base from Base 10 ***/
		
		while ( valueBase10 > 0 )
		{
			remainder = valueBase10 % newBase;
			valueBase10 = valueBase10 / newBase;
			
			newValue = newBaseCharacter[ remainder ] + newValue;
		}
		
		return newValue;
	}	
	
	private static void convertValueToNewBase( int newBase ) 
	{
//		/*** Local Variables ***/
//		 
//		int actualValue = 0;
//		
//		/*** Convert existing current value to new base ***/
//		
//		if ( this.currentValue != 0 )
//		{
////			if ( this.base > 10 )
////				actualValue = convertCurrentValueToBase10();
//			
//			if ( newBase > this.base )
//			{
//				
//			}
//			else   // newBase < this.base )
//			{
//				if ( this.currentValue >= newBase )	
//				{
//					
//				}
//			}
//		}
	}
	
	/*** Application ***/
	
	public static void main( String args[] )
	{
	    int valueBase10 = 160;
	    String str = "";
	    int valueBase = 0;
	
	    for ( int base = 2; base <= 16; base++ )
	    {
		    System.out.print( "Base10: " + valueBase10 + 
		    		            " Base" + base + ": " );
		    
		    str = MultiBaseCalculatorState.convertFromBase10( valueBase10, base );
		    
		    System.out.println( str + " Base10: " +
		    		MultiBaseCalculatorState.convertToBase10( base, str ) );
	    }
	}
}