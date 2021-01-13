package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {
	private static final Font FONT = new Font("Calibri", Font.CENTER_BASELINE, 15);

	public HelpPanel(JPanel gamefield) {
		JLabel tekst = new JLabel("<html>The objective is to fill a 9x9 grid with digits so that each column, "
				+ "each row, and each of the nine 3x3 subgrids that compose the grid "
				+ "(also called \"boxes\", \"blocks\", or \"regions\") "
				+ "contain all of the digits from 1 to 9. The puzzle setter provides a partially completed grid,"
				+ " which for a well-posed puzzle has a single solution.<html>");
		tekst.setFont(FONT);
		JPanel helpPane = new JPanel();
		JButton helpReturn = new JButton("Powrót");
		helpPane.setPreferredSize(new Dimension(500, 200));
		helpReturn.setPreferredSize(new Dimension(50, 30));
		helpPane.setLayout(new BorderLayout(30, 30));
		helpPane.add(new JLabel(" "), BorderLayout.EAST);
		helpPane.add(new JLabel(" "), BorderLayout.WEST);
		helpPane.add(tekst, BorderLayout.CENTER);
		// helpPane.add(helpReturn, BorderLayout.SOUTH);
		add(helpPane);


	}

}
