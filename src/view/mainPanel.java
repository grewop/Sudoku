package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import sudoku.project.MyTextField;

public class MainPanel extends JPanel {
	
	

	public MainPanel(MyTextField[][] mainField, JPanel gridPanelMain, HighlightMouseListener hml) {
		
		
		// generowanie pól textowych 
				for (int a = 0; a < 3; ++a) {
					for (int b = 0; b < 3; ++b) {
						JPanel panelForTextField = new JPanel();
						for (int i = 0; i < 3; ++i) {
							for (int j = 0; j < 3; ++j) {
								MyTextField fieldText = new MyTextField(((a * 3) + i),((b * 3) + j));
								fieldText.setHorizontalAlignment(JTextField.CENTER);
								mainField[((a * 3) + i)][((b * 3) + j)] = fieldText;
								//mainField[((a * 3) + i)][((b * 3) + j)].setText(" ");
								fieldText.addKeyListener(new KeyAdapter() {
									public void keyTyped(KeyEvent e) {
										char input = e.getKeyChar();
										String text = fieldText.getText();
										if ((input < '1' || input > '9') && input != '\b'){
											e.consume();
											//System.out.println("NIE CYFRA");
											
										}
//										if(text.length() == 1) {
//												e.consume();
//											}
										//limit znakow bez potrzeby backspace
										fieldText.setDocument(new PlainDocument());
										//działa w tym przypadku 
									}
								});
								fieldText.addMouseListener(hml);	
								fieldText.setPreferredSize(new Dimension(50,50));
								panelForTextField.add(fieldText);
								
							}
						}
						hml.setMainfield(mainField);
						panelForTextField.setLayout(new GridLayout(3,3,5,5));
						panelForTextField.setBorder(BorderFactory.createLineBorder(Color.black,4));
						gridPanelMain.add(panelForTextField);
					}
				}
				 
				
				add(gridPanelMain);

	
}	
	}