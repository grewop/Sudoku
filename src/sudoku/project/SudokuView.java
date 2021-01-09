package sudoku.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.HighlightMouseListener;
import view.aboutMe;
import view.helpPanel;
import view.mainPanel;

public class SudokuView extends JFrame {
	private aboutMe aboutAuthor;
	private helpPanel hPanel;
	private mainPanel mPanel;
	JButton finish = new JButton("Sprawdz");
	JButton solveGame = new JButton("Rozwiąż gre");
	JButton newGame = new JButton("Nowa gra");

	JMenu menu = new JMenu("Menu");
	JMenu info = new JMenu("Autor");
	JMenu help = new JMenu("Pomoc");

	JMenuItem exit = new JMenuItem("Wyjdz");
	JMenuItem saveGame = new JMenuItem("Zapisz gre");
	JMenuItem loadGame = new JMenuItem("Wczytaj gre");
	JMenuBar menuBar = new JMenuBar();

	JPanel gameField = new JPanel();

	JPanel flowPanel = new JPanel();

	private static final Font FONT = new Font("Verdana", Font.CENTER_BASELINE, 10);

	HighlightMouseListener hml = new HighlightMouseListener();

	// SudokuController test = new SudokuController(null, null);
	SudokuModel model = new SudokuModel();
	private MyTextField mainField[][] = new MyTextField[9][9];
	
	// panaele na grupy texfield 3x3
	JPanel gridPanelMain = new JPanel(new GridLayout(3, 3, -1, -1));

	JFrame frame;
	JFrame frame3;
	JFrame frame2;

	public SudokuView() {

		this.frame = new JFrame("Sudoku");
		this.frame2 = new JFrame("Sudoku - pomoc");
		this.frame3 = new JFrame("Sudoku - autor");

		frame.setSize(900, 700);
		frame2.setSize(900, 300);
		frame3.setSize(900, 300);

		// dodawanie z oddzielnych klas panelu help i planszy
		hPanel = new helpPanel(gameField);
		mPanel = new mainPanel(mainField, gridPanelMain, hml);
		aboutAuthor = new aboutMe(gameField);
		// dodawanie menu
		menuBar.add(menu);
		menuBar.add(info);
		menuBar.add(help);

		menu.add(exit);
		menu.add(saveGame);
		menu.add(loadGame);

		gameField.add(mPanel, BorderLayout.WEST);

		flowPanel.setLayout(new BoxLayout(flowPanel, BoxLayout.Y_AXIS));

		newGame.setMaximumSize(new Dimension(200, 100));
		finish.setMaximumSize(new Dimension(200, 100));
		solveGame.setMaximumSize(new Dimension(200, 100));

		newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		finish.setAlignmentX(Component.CENTER_ALIGNMENT);
		solveGame.setAlignmentX(Component.CENTER_ALIGNMENT);

		flowPanel.add(newGame);
		flowPanel.add(Box.createRigidArea(new Dimension(5, 10)));
		flowPanel.add(finish);
		flowPanel.add(Box.createRigidArea(new Dimension(5, 10)));
		flowPanel.add(solveGame);

		frame.add(flowPanel, BorderLayout.SOUTH);
		frame.add(gameField);
		frame.setJMenuBar(menuBar);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public JButton getNewGame() {
		return newGame;
	}

	public void setNewGame(JButton newGame) {
		this.newGame = newGame;
	}

	public JButton getSolveGame() {
		return solveGame;
	}

	public void setSolveGame(JButton solveGame) {
		this.solveGame = solveGame;
	}

	public JButton getFinisghGame() {
		return finish;
	}

	public void setfinishGame(JButton finish) {
		this.finish = finish;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}

	public JMenuItem getMenuSaveGame() {
		return saveGame;
	}

	public void setMenuSaveGame(JMenuItem saveGame) {
		this.saveGame = saveGame;
	}

	public JMenuItem getMenuLoadGame() {
		return loadGame;
	}

	public void setMenuLoadGame(JMenuItem loadGame) {
		this.loadGame = loadGame;
	}

	public JMenu getMenuHelpGame() {
		return help;
	}

	public void setMenuHelpGame(JMenu help) {
		this.help = help;
	}

	public JMenu getMenuInfoGame() {
		return info;
	}

	public void setMenuInfoGame(JMenu info) {
		this.info = info;
	}

	public void setMainfield(MyTextField[][] jTextFields) {
		this.mainField = jTextFields;
	}

	public MyTextField[][] getMainField() {
		return mainField;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrameHelp(JFrame frame2) {
		this.frame2 = frame2;
	}

	public JFrame getFrameHelp() {
		return frame2;
	}

	public void setFrameME(JFrame frame3) {
		this.frame3 = frame3;
	}

	public JFrame getFrameME() {
		return frame3;
	}

	public void sethPanel(helpPanel hPanel) {
		this.hPanel = hPanel;
	}

	public helpPanel gethPanel() {
		return hPanel;
	}

	public void setAuthorPanel(aboutMe aboutAuthor) {
		this.aboutAuthor = aboutAuthor;
	}

	public aboutMe getAuthorPanel() {
		return aboutAuthor;
	}

}
