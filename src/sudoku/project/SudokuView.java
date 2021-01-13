package sudoku.project;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import view.HighlightMouseListener;
import view.AboutMe;
import view.HelpPanel;
import view.MainPanel;

public class SudokuView extends JFrame {
	private AboutMe aboutAuthor;
	private HelpPanel hPanel;
	private MainPanel mPanel;
	private JButton finish = new JButton("Sprawdz");
	private JButton solveGame = new JButton("Rozwiąż gre");
	private JButton newGame = new JButton("Nowa gra");
	private JButton gameFieldOne = new JButton("1");
	private JButton gameFieldTwo = new JButton("2");
	private JButton gameFieldThree = new JButton("3");
	private JButton gameFieldFour = new JButton("4");
	private JMenu menu = new JMenu("Menu");
	private JMenu info = new JMenu("Autor");
	private JMenu help = new JMenu("Pomoc");

	private JMenuItem exit = new JMenuItem("Wyjdz");
	private JMenuItem saveGame = new JMenuItem("Zapisz gre");
	private JMenuItem loadGame = new JMenuItem("Wczytaj gre");
	private JMenuBar menuBar = new JMenuBar();

	private JPanel gameField = new JPanel();

	private JPanel flowPanel = new JPanel();

	private static final Font FONT = new Font("Verdana", Font.CENTER_BASELINE, 10);

	private HighlightMouseListener hml = new HighlightMouseListener();

	// SudokuController test = new SudokuController(null, null);
	
	private MyTextField mainField[][] = new MyTextField[9][9];
	
	private int[][] gameLayoutOne = {
			{0 , 0 , 6 , 7 , 0 , 3 , 0 , 0 ,0 },
			{0 , 0 , 0 , 0 , 0 , 0 , 6 , 7 ,0 },
			{0 , 0 , 0 , 0 , 6 , 5 , 1 , 0 ,9 },
			{0 , 7 , 2 , 5 , 0 , 4 , 8 , 9 ,0 },
			{8 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,6 },
			{0 , 9 , 3 , 2 , 0 , 6 , 7 , 4 ,0 },
			{5 , 0 , 8 , 4 , 7 , 0 , 0 , 0 ,0 },
			{0 , 2 , 9 , 0 , 0 , 0 , 0 , 0 ,0 },
			{0 , 0 , 0 , 6 , 0 , 9 , 4 , 0 ,0 }
			};
		private	int[][] gameLayoutTwo = {
					  { 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					  { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
					  { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
					  { 0, 5, 0, 0, 0, 0, 0, 0, 0 },
					  { 0, 0, 0, 0, 0, 0, 7, 0, 0 },
					  { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
					  { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
					  { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
					  { 0, 9, 0, 0, 0, 0, 3, 0, 0 } 
					};		
		private	int[][] gameLayoutThree = {
				  { 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 2, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 4, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 5, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 6, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 7, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 8, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 9 } 
				};	
		private	int[][] gameLayoutFour = {
				  { 0, 2, 0, 3, 0, 4, 0, 1, 0 },
				  { 4, 1, 0, 0, 0, 5, 9, 0, 0  },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 2  },
				  { 7, 5, 0, 9, 4, 0, 2, 3, 0  },
				  { 0, 4, 3, 0, 0, 0, 6, 9, 0  },
				  { 0, 9, 2, 0, 8, 3, 0, 4, 7  },
				  { 2, 0, 0, 0, 0, 0, 0, 0, 0  },
				  { 0, 0, 4, 2, 0, 0, 0, 5, 9  },
				  { 0, 3, 0, 1, 0, 8, 0, 2, 0  } 
				};	
	// panaele na grupy texfield 3x3
	private JPanel gridPanelMain = new JPanel(new GridLayout(3, 3, -1, -1));
	private JPanel buttonPanelGameField = new JPanel(new FlowLayout());
	private	JFrame frame;
	private	JFrame frame3;
	private	JFrame frame2;

	public SudokuView() {

		this.frame = new JFrame("Sudoku");
		this.frame2 = new JFrame("Sudoku - pomoc");
		this.frame3 = new JFrame("Sudoku - autor");

		frame.setSize(900, 750);
		frame2.setSize(900, 300);
		frame3.setSize(900, 300);

		// dodawanie z oddzielnych klas panelu help i planszy
		hPanel = new HelpPanel(gameField);
		mPanel = new MainPanel(mainField, gridPanelMain, hml);
		aboutAuthor = new AboutMe(gameField);
		// dodawanie menu
		menuBar.add(menu);
		menuBar.add(info);
		menuBar.add(help);

		menu.add(exit);
		menu.add(saveGame);
		menu.add(loadGame);
		buttonPanelGameField.add(new Label("Wybierz plansze: "));
		buttonPanelGameField.add(gameFieldOne);
		buttonPanelGameField.add(gameFieldTwo);
		buttonPanelGameField.add(gameFieldThree);
		buttonPanelGameField.add(gameFieldFour);
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
		frame.add(buttonPanelGameField, BorderLayout.NORTH);
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
	public JButton getGameFieltOne() {
		return gameFieldOne;
	}

	public JButton getGameFieltTwo() {
		return gameFieldTwo;
	}
	public JButton getGameFieltThree() {
		return gameFieldThree;
	}
	public JButton getGameFieltFour() {
		return gameFieldFour;
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

	public void sethPanel(HelpPanel hPanel) {
		this.hPanel = hPanel;
	}

	public HelpPanel gethPanel() {
		return hPanel;
	}

	public void setAuthorPanel(AboutMe aboutAuthor) {
		this.aboutAuthor = aboutAuthor;
	}

	public AboutMe getAuthorPanel() {
		return aboutAuthor;
	}
	public int[][] getGameLayoutOne(){
		return gameLayoutOne;
		
	}
	public int[][] getGameLayoutTwo(){
		return gameLayoutTwo;
		
	}
	public int[][] getGameLayoutThree(){
		return gameLayoutThree;
		
	}
	public int[][] getGameLayoutFour(){
		return gameLayoutFour;
		
	}

}
