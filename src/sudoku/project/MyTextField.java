package sudoku.project;

import javax.swing.JTextField;

public class MyTextField extends JTextField {
	private int xPos;
    private int yPos;
   
    public int getXPos() {
        return xPos;
    }
	public int getYPos() {
	        return yPos;
	    }
	public MyTextField(int xPos,int yPos) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
}
