/*
 * @author Steven Imas
 * Basic JFrame set-up
 * 
 * * runs the MultiBaseCalculatorGui() constructor which sets JFrame 
 * window to "Project 1 - MultiBase Calculator" and then sets it
 * visible and usable.
 * 
 */



import javax.swing.JFrame;

// this class probably won't need to change much: most of the 'action' is in BasePanel and BaseCalc


public class MultiBaseCalculatorGUI 
{
	/*** Constructor ***/

	private MultiBaseCalculatorGUI() 
	{
		JFrame mbCalculator = new JFrame( "Project 1 - MultiBase Calculator" );

		mbCalculator.add( new MultiBaseCalculatorPanel() );
		
		setFrameAttributes( mbCalculator );
	}
	
	private void setFrameAttributes( JFrame baseGUI )
	{
		baseGUI.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		baseGUI.setSize( 500, 350 );
		baseGUI.setVisible( true );
	}
	
    /*** Application ***/
	
	public static void main(String[] args) 
	{
		new MultiBaseCalculatorGUI();
	}
}
