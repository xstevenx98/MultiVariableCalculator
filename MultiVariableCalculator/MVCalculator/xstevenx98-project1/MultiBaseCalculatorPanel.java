/*
 * @author Steven Imas
 * 
 *TheMultiBaseCalculatorPanel class  declares and adds the 
 *number buttons , operators,  textField, and a JSlider used to
 *choose which base the user wants to calculate in.
 *When using the JSlider, only the numbers available in that
 *base will appear.  A baseCalculator object is created 
 *as well and it is used to send what base is currently
 *being used and to append values of the buttons being used
 *by the actionListener.  The calculator waits for numbers to
 *be stored in the result textField which the user can
 *manipulate by entering a second number and which operation 
 *the user wants to perform.  The result in the textField
 *can then be manipulated with the JSlider to switch between
 *bases of the number.
 *		 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MultiBaseCalculatorPanel extends JPanel implements ActionListener, ChangeListener
{
	public static int DEFAULT_BASE_NUMBER = 10;
	
	/*** Class Variables ***/
											//Input
	private JSlider    jslBase    = null;
	private JTextField jteInput   = null;
											//Digits and Operators
	private JButton jteDigitButtons[] = null;	
	private JButton jteOperatorButtons[] = null;	
											//Process
	private JButton jbuClear  = null;
											//Output	
	private JTextField jteResults = null;
											//Computational Object
	private MultiBaseCalculatorState baseCalculator = null;
	
    /*** Constructor ***/
	/*
	 *    @param jslBase creates the jSlider, and puts it in the north quadrant
	 *  
	 *  @param jpaBase creates the GUI panel which the user can 
	 *   		interact with by calculating operations and switching
	 *   		the base of the resulting equation.
	 *  
	 *  @param jpaButton creates the GUI buttons 1-9, A-F, and adds 
	 *  		them to the center of the panel. Then the arithmetic operators
	 *  		 are created and added to the right side of the panel.
	 *   
	 *   @param jpaProcess adds the containers together to the big
	 *   		container which is the panel.
	 *   
	 *   @see 	MultiBaseCalculator GUI
	 */
	
    public MultiBaseCalculatorPanel()
    {
	   /*** Local Variables ***/
    	
       JPanel jpaBase    = null;
       JPanel jpaButton  = null;
       JPanel jpaProcess = null;
       
       /*** Instantiate GUI containers ***/
       
       jpaBase    = createBasePanel();
       jpaButton  = createButtonPanel();
       jpaProcess = createProcessPanel();
       
       /*** Add containers to big container ***/
       
       setLayout( new BorderLayout() );
       
       add( "North",  jpaBase );
       add( "Center", jpaButton );   
       add( "South",  jpaProcess );  
       
       /*** Instantiate Computational object ***/
       
       baseCalculator = new MultiBaseCalculatorState( DEFAULT_BASE_NUMBER );
       
       changeDigitsForBaseSelected ( DEFAULT_BASE_NUMBER, true );
       enableOperators( false );
    }
    
    /*** Panel helper methods ***/
    
    private JPanel createBasePanel()
    {
    	/*** Local Variables ***/
    	
    	JPanel jpaBase        = null;
        
        /*** Instantiate GUI containers ***/  
    	
    	jpaBase    = new JPanel();
    	jpaBase.setLayout( new BorderLayout() );
    	
    	jslBase    = createJSlider();
    	jteResults = new JTextField( "0", 20 );
    	jteResults.setHorizontalAlignment( JTextField.RIGHT );
    	jteResults.setEditable( false );
    	jteResults.setForeground( Color.BLUE );
    	
    	jteInput = new JTextField( 20 );
    	jteInput.setHorizontalAlignment( JTextField.RIGHT );    	
    	jteResults.setEditable( false );
    	
        /*** Add containers to big container ***/   
    	
    	jpaBase.add( "North",  jslBase    );
    	jpaBase.add( "West",   new JLabel( "     " )  );   	
    	jpaBase.add( "Center", jteResults );  
      	jpaBase.add( "East",   new JLabel( "     " )  );
    	jpaBase.add( "South",  jteInput   );  
    	
        /*** Return container ***/
    	
    	return jpaBase;
    }
    
    private JSlider createJSlider()
    {
    	/*** Local Constants ***/
    	
    	final int BASE_MIN  = 2;
    	final int BASE_MAX  = 16;
    	
    	/*** Local Variables ***/ 	
    	
    	JSlider jslSlider = null;
    	
    	/*** Instantiate JSlider ***/
    	
    	jslSlider = new JSlider( JSlider.HORIZONTAL, BASE_MIN, BASE_MAX, DEFAULT_BASE_NUMBER );
    	
    	jslSlider.setMinorTickSpacing( 1 );
    	jslSlider.setMajorTickSpacing( 2 );
    	jslSlider.setPaintTicks( true );
    	jslSlider.setPaintLabels( true );  
    	  	
    	jslSlider.addChangeListener( this );
    	
    	return jslSlider;
    }
    
    private JPanel createButtonPanel()
    {
    	/*** Local Variables ***/
    	
    	JPanel jpaButton         = null;
    	
        JPanel jpaDigitButton    = null;
        JPanel jpaOperatorButton = null;
               
        /*** Instantiate GUI containers ***/
        
        jpaButton    = new JPanel();
        
        jpaDigitButton    = createButtonDigitPanel();
        jpaOperatorButton = createButtonOperatorPanel();
    	    	
        /*** Add containers to big container ***/   
    	
    	jpaButton.add( "West", jpaDigitButton     );
    	jpaButton.add( "Center", new JLabel( "" ) );   	
    	jpaButton.add( "East", jpaOperatorButton  );    	
    	
        /*** Return container ***/
    	
    	return jpaButton;    	
    }
    
    private JPanel createButtonDigitPanel()
    {
    	/*** Local Variables ***/
    	
    	JPanel jpaButtonDigit     = null;
        
        /*** Instantiate GUI containers ***/  
    	
    	jpaButtonDigit    = new JPanel();   	
    	jpaButtonDigit.setLayout( new GridLayout( 6, 3 ) );
  
    	/*** Create all buttons with appropriate digits (including A-F) ***/
    	
    	jteDigitButtons = new JButton[ 16 ];
  
    	for ( int i = 0; i <= 9; i++ )
    	{   		
        	jteDigitButtons[ i ] = new JButton( (char) ( i + '0' ) + "" );   //1-9 buttons 
        	jteDigitButtons[ i ].addActionListener( this );
    	}
    	   	
    	for ( int i = 10; i < jteDigitButtons.length; i++ )
    	{
        	jteDigitButtons[ i ] = new JButton( (char) ( i - 10 + 'A' ) + "" );  //A-F buttons  
        	jteDigitButtons[ i ].addActionListener( this );        	
    	}
    	  	    	
        /*** Add containers to big container ***/   
    	   											// 1st row Hex D-F 
    	jpaButtonDigit.add( jteDigitButtons[ 13 ] );
    	jpaButtonDigit.add( jteDigitButtons[ 14 ] );
    	jpaButtonDigit.add( jteDigitButtons[ 15 ] );    	
													// 2nd row Hex A-C 
		jpaButtonDigit.add( jteDigitButtons[ 10 ] );
		jpaButtonDigit.add( jteDigitButtons[ 11 ] );
		jpaButtonDigit.add( jteDigitButtons[ 12 ] );  	
													// 3rd row 
    	jpaButtonDigit.add( jteDigitButtons[ 7 ] );
    	jpaButtonDigit.add( jteDigitButtons[ 8 ] );
    	jpaButtonDigit.add( jteDigitButtons[ 9 ] );   	
													// 4th row
		jpaButtonDigit.add( jteDigitButtons[ 4 ] );
		jpaButtonDigit.add( jteDigitButtons[ 5 ] );
		jpaButtonDigit.add( jteDigitButtons[ 6 ] );
													// 5th row
		jpaButtonDigit.add( jteDigitButtons[ 1 ] );
		jpaButtonDigit.add( jteDigitButtons[ 2 ] );
		jpaButtonDigit.add( jteDigitButtons[ 3 ] );
													// 6th row
		jpaButtonDigit.add( new JLabel( "   " ) );
		jpaButtonDigit.add( jteDigitButtons[ 0 ] );
		jpaButtonDigit.add( new JLabel( "   " ) );		
				  	
        /*** Return container ***/
    	
    	return jpaButtonDigit;    	
    }
    
    private JPanel createButtonOperatorPanel()
    {
    	/*** Local Variables ***/
    	
    	JPanel jpaButtonDigit     = null;
        
        /*** Instantiate GUI containers ***/  
    	
    	jpaButtonDigit    = new JPanel();   	
    	jpaButtonDigit.setLayout( new GridLayout( 5, 1 ) );
  
    	/*** Create all buttons with appropriate digits (including A-F) ***/
    	
    	jteOperatorButtons = new JButton[ 4 ];
  
        jteOperatorButtons[ 0 ] = new JButton( "+" );
        jteOperatorButtons[ 0 ].addActionListener( this );
        jteOperatorButtons[ 1 ] = new JButton( "-" );
        jteOperatorButtons[ 1 ].addActionListener( this );          
        jteOperatorButtons[ 2 ] = new JButton( "*" );
        jteOperatorButtons[ 2 ].addActionListener( this );  
        jteOperatorButtons[ 3 ] = new JButton( "/" );
        jteOperatorButtons[ 3 ].addActionListener( this );          
      
        /*** Add containers to big container ***/   
    	
    	for ( int i = 0; i < jteOperatorButtons.length; i++ )
    	{
        	jpaButtonDigit.add( jteOperatorButtons[ i ]  );       		
    	}
    	jpaButtonDigit.add( new JLabel( "   " ) );
    		  	
        /*** Return container ***/
    	
    	return jpaButtonDigit;    	
    }    
    
    private JPanel createProcessPanel()
    {
    	/*** Local Variables ***/
    	
    	JPanel jpaProcess = null;
        
        /*** Instantiate GUI containers ***/  
    	
    	jpaProcess    = new JPanel();
    	
    	jbuClear = new JButton( "Clear" );
    	jbuClear.addActionListener( this );
    	    	
        /*** Add containers to big container ***/   
    	
    	jpaProcess.add( "North", jbuClear );  	
    	
        /*** Return container ***/
    	
    	return jpaProcess;
    }
    
    /*** Handle Action Events -- Button Clicks ***/

	@Override
	public void actionPerformed( ActionEvent e ) 
	{   	
		if ( e.getSource() == jbuClear )
		{
			processClearButton();
			return;
		}
       
		/*** User clicked digit so operator can now be clicked ***/
		
		enableOperators( true );
		
        /*** Find out which action event occurred? ***/
            
	   	for ( int i = 0; i < jteDigitButtons.length; i++ )
	   	{
	       	if ( e.getSource() == jteDigitButtons[ i ]  )
	       	{
	       		/*** Display digit on calculate ***/
	       		
	       		displayDigitOnCalculator( jteDigitButtons[ i ] );
	       		return;
	       	}
	   	}  
	   	
	   	for ( int i = 0; i < jteOperatorButtons.length; i++ )
	   	{
	       	if ( e.getSource() == jteOperatorButtons[ i ]  )
	       	{
	       		/*** Display digit on calculate ***/
	       		
	       		performOperation( jteOperatorButtons[ i ] );
	       		return;
	       	}
	   	} 
	}
	
	/*** ActionEvent helper methods ***/
	
	private void processClearButton()
	{
		jteResults.setText( "0" );
		jteInput.setText( "" );
		
		baseCalculator.resetCurrentValue();
		
		/*** No input so operators should not be able to be clicked ***/
		
		enableOperators( false );
	}
	
	private void displayDigitOnCalculator( JButton jbuSelected )
	{
		jteInput.setText( jteInput.getText() + jbuSelected.getText() );
	}
		
	private void performOperation( JButton jbuOperator )
	{
		/*** Local Variables ***/
		
		String newResults = null;
		String currentInput = null;
		String currentOperator = null;
		
		/*** Convert current input to a number ***/
		
		currentInput = jteInput.getText();
		
		/*** Perform operation ***/
		
		currentOperator = jbuOperator.getText();
		
		if ( currentInput.length() >= 1 )
		{
		    switch ( currentOperator )
			{
			     case "+" : newResults = baseCalculator.add( currentInput );
			    	        break;
			     
			     case "-" : newResults = baseCalculator.subtract( currentInput );
			    	        break;
			     
			     case "*" : newResults = baseCalculator.multiply( currentInput );
			    	        break;		     
			     
			     case "/" : newResults = baseCalculator.divide( currentInput );
			    	        break;
			}
		}

	    /*** Display results ***/
	    
	    jteInput.setText( "" );
	    jteResults.setText( "" + newResults );
	}
	
	private void enableOperators( boolean enabledButton )
	{
		/*** Iterate thru JButton array to only enable buttons for that base ***/
		
		for ( int i = 0; i < jteOperatorButtons.length; i++ )
		{		
		   jteOperatorButtons[ i ].setEnabled( enabledButton );		
		}		
	}
	
	/*** ChangeEvent -- user moves slider ***/
	
	@Override
	public void stateChanged( ChangeEvent e )
	{
		/*** User moved slider, get slider value ***/
		
		int newBase = 0;
		int oldBase = baseCalculator.getBase();
		int valueBase10 = 0;
		
		/*** Determine what buttons should be displayed for base user selected ***/
		
		if ( e.getSource() == jslBase )
		{
			newBase = jslBase.getValue();
			
			/*** Change buttons to display based on base number ***/
			
			changeDigitsForBaseSelected( newBase, true );
			
			valueBase10 = 
			        MultiBaseCalculatorState.convertToBase10( oldBase, baseCalculator.getCurrentValue() );
			
			baseCalculator.setBase( newBase );
			baseCalculator.setCurrentValue( 
					MultiBaseCalculatorState.convertFromBase10( valueBase10, newBase ) );			
			
			jteResults.setText( baseCalculator.getCurrentValue() );		
			
			/*** No input so operators cannot be clicked ***/
			
			enableOperators( false );
	    }
	}	
	
	/*** ChangeEvent helper methods ***/
	
	private void changeDigitsForBaseSelected ( int baseNumber, boolean enabledButton )
	{
		/*** Iterate thru JButton array to only enable buttons for that base ***/
		
		for ( int i = 0; i < jteDigitButtons.length; i++ )
		{		
			if ( i < baseNumber )
			    jteDigitButtons[ i ].setEnabled( enabledButton );
			else
			    jteDigitButtons[ i ].setEnabled( !enabledButton );				
		}		
	}
}
