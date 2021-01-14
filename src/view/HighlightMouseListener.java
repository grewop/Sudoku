package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

import sudoku.project.MyTextField;


public class HighlightMouseListener extends MouseAdapter {
	
	
	private MyTextField[][] mainField;
	private JTextField previous;
	private Border borderEntere = BorderFactory.createLineBorder(Color.red, 2);
	private Border borderExited = BorderFactory.createLineBorder(Color.black, 1);
	
	
	public void mouseEntered(MouseEvent e) {
		MyTextField source = (MyTextField) e.getSource();
		int x =  source.getXPos();
		int y =  source.getYPos();
		 if (!(source instanceof JTextField)) {
             return;
         }
		 JTextField field = (JTextField) source;

         previous = field;
         field.setBorder(borderEntere);
         colorFieldHover(mainField, x, y, borderEntere);
  
        }
     
	public void mouseExited(MouseEvent e) {
		MyTextField source = (MyTextField) e.getSource();
		int x =  source.getXPos();
		int y =  source.getYPos();
		 if (!(source instanceof JTextField)) {
            return;
        }
		 JTextField field = (JTextField) source;
//       
        previous = field;
        field.setBorder(borderExited);
        colorFieldHover(mainField, x, y, borderExited);
	}
	public void colorFieldHover (MyTextField[][] jTextFields, int row , int col, Border border) {
		// podwietla rzad 
		for(int i = 0; i < 9 ; i++ ) {
			 jTextFields[row][i].setBorder(border);
        }
		//podswietla kolumne 
		 for(int i = 0 ; i< 9;i++) {
			 jTextFields[i][col].setBorder(border);
		 }
		 int rowFrom = getminicord(row);
		 int colFrom = getminicord(col);
		 //podswietla kwadrat 
		for(int i = rowFrom; i < rowFrom + 3; i++ ) {
			for(int j = colFrom; j < colFrom + 3; j++ ) {
				jTextFields[i][j].setBorder(border);
			}
		} 
		
		
	}
	private int getminicord(int val) {
		return val - (val%3);
	}
	public void setMainfield(MyTextField[][] jTextFields){
		this.mainField = jTextFields;
	}
}


