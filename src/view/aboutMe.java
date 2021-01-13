package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutMe extends JPanel {
	private static final Font FONT = new Font("Calibri", Font.CENTER_BASELINE, 25);

	public AboutMe(JPanel gamefield) {
		JLabel tekst = new JLabel("<html>Przemysław Rzempołuch.<html>");
		tekst.setFont(FONT);
		JPanel mePane = new JPanel();

		mePane.setPreferredSize(new Dimension(300, 200));

		mePane.setLayout(new BorderLayout(30, 30));
		mePane.add(new JLabel(" "), BorderLayout.EAST);
		mePane.add(new JLabel(" "), BorderLayout.WEST);
		mePane.add(tekst, BorderLayout.CENTER);
		
		add(mePane);
	}
}
