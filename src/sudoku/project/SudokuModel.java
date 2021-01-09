package sudoku.project;

import java.awt.Color;
import java.awt.Container;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.prefs.Preferences;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.JTextFieldLimit;
import view.aboutMe;
import view.helpPanel;

public class SudokuModel {
	private MyTextField[][] mainField;
	private SudokuView sudokuView;
	private SudokuModel sudokuModel;
	Border borderEntere = BorderFactory.createLineBorder(Color.red, 2);
	Border borderExited = BorderFactory.createLineBorder(Color.black, 1);
	private JTextField previous;

	public void exit(Container container) {
		String[] options = { "Tal", "zapisz gre", "Nie" };
		int selection = JOptionPane.showOptionDialog(container, "Czy napewno chcesz zakończyć grę?", "koniec",
				JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
		if (selection == 0) {
			System.exit(0);
		} else if (selection == 1) {

		}
	}

	// zapisywanie samej tabeli
	public void saveGame(MyTextField[][] jTextFields) throws IOException {
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		f.setCurrentDirectory(new File("/D:/"));//zaczynami na D
		f.showSaveDialog(null);
		
		StringBuilder builder = new StringBuilder();
		// System.out.print("wykonuje sie");
		for (int i = 0; i < jTextFields.length; i++)// rzad
		{
			for (int j = 0; j < jTextFields.length; j++)// kolumna
			{
				builder.append(jTextFields[i][j].getText() + "");// dodwanai do stringa wyjscia
				if (j < jTextFields.length - 1)// dodaj  przecinek do konca jesli to nie koniec
					builder.append(",");
			}
			builder.append("\n");// dodaj linie na koncu
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(f.getCurrentDirectory()+f.getSelectedFile().getName()+ ".txt"));
		//System.out.print(f.getCurrentDirectory()+f.getSelectedFile().getName()+ ".txt");
		writer.write(builder.toString());
		writer.close();
	}

	public void loadGame(MyTextField[][] jTextFields) throws NumberFormatException, IOException {
		Preferences pref = Preferences.userRoot().node(getClass().getName());

		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		f.setCurrentDirectory(new File("/D:/"));
		f.showSaveDialog(null);

//      System.out.println(f.getCurrentDirectory());
//      System.out.println(f.getSelectedFile());
		File savedGameFile = f.getSelectedFile();
		int[][] board = new int[9][9];
		BufferedReader reader = new BufferedReader(new FileReader(savedGameFile));
		String line = "";
		int row = 0;
		while ((line = reader.readLine()) != null) {
			String[] cols = line.split(","); 
			int col = 0;
			for (String c : cols) {
				// board[row][col] = Integer.parseInt(c);
				jTextFields[row][col].setText(c);
				col++;

			}
			row++;
		}
		reader.close();
	}

	public void clearGrid(MyTextField[][] jTextFields) {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				jTextFields[x][y].setText("");

			}
		}

	}

	public void finishGame(JFrame frame, MyTextField[][] jTextFields) {
		int sumALL = 0;
		int sumRow[] = new int[9];
		int sumCol[] = new int[9];
		int sum3x3[][] = new int[3][3];

		for (int row = 0; row < 9; row++) {

			for (int col = 0; col < 9; col++) {
				// check all fields
				if (jTextFields[row][col].getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Dokończ grę");
					return;
				}
				sumALL = sumALL + Integer.parseInt(jTextFields[row][col].getText());
				// check row
				sumRow[row] += Integer.parseInt(jTextFields[row][col].getText());

			}
		}
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				if (jTextFields[row][col].getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Dokończ grę");
					return;
				}
				// check columss
				sumCol[col] += Integer.parseInt(jTextFields[row][col].getText());
			}
		}
		for (int a = 0; a < 3; ++a) {
			for (int b = 0; b < 3; ++b) {

				for (int i = 0; i < 3; ++i) {
					for (int j = 0; j < 3; ++j) {

						sum3x3[a][b] += Integer.parseInt(jTextFields[((a * 3) + i)][((b * 3) + j)].getText());

					}

				}
				System.out.println(sum3x3[a][b] + " a = " + a + " b = " + b);
			}
		}
		// zamiana tablicy 2d na 1d dla łatwiejszego sprawdzania
		int[] cubes = Stream.of(sum3x3).flatMapToInt(IntStream::of).toArray();

		// Sprawdzenie ostatnie
		for (int i = 0; i < 9; i++) {
			int box = cubes[i];
			int z = sumCol[i];
			int y = sumRow[i];

			System.out.println("i = " + i + " z = " + z + " y = " + y);

			if (z != 45 || y != 45 || box != 45) {

				JOptionPane.showMessageDialog(frame, "Gdzieś jest błąd");
				break;
			}
			if (i == 8 && sumALL == 405) { // i == 8 oznacza ze przeszlo bez bledow przez wszystko
				JOptionPane.showMessageDialog(frame, "Gratulacje rozwiązałeś sudoku");

			}
		}
//		System.out.print(Arrays.toString(sumCol));
//		System.out.print(Arrays.toString(sumRow));

	}
// działa ale moze bardzo długo 
	public boolean solve(MyTextField[][] jTextFields, JFrame frame) {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				if (jTextFields[row][column].getText().equals("")) {
					for (int k = 1; k <= 9; k++) {

						String c = Integer.toString(k);
						jTextFields[row][column].setText(c);
						if (isValid(jTextFields, row, column) && solve(jTextFields, frame)) {
							// System.out.println("solve true");
							return true;

						}
						jTextFields[row][column].setText("");
					}
					// JOptionPane.showMessageDialog(frame, "Nie można rozwiązać");
					// System.out.println("solve false");
					return false;
				}
			}
		}
		// System.out.println("solve true");
		return true;
	}

	public boolean isValid(MyTextField[][] jTextFields, int row, int column) {
		// System.out.println("isValid");
		return (rowConstraint(jTextFields, row) && columnConstraint(jTextFields, column)
				&& subsectionConstraint(jTextFields, row, column));
	}

	public boolean rowConstraint(MyTextField[][] jTextFields, int row) {
		boolean[] constraint = new boolean[9];
		// System.out.println("rowConstraint");
		return IntStream.range(0, 9).allMatch(column -> checkConstraint(jTextFields, row, constraint, column));
	}

	public boolean columnConstraint(MyTextField[][] jTextFields, int column) {
		boolean[] constraint = new boolean[9];
		// System.out.println("columnConstraint");
		return IntStream.range(0, 9).allMatch(row -> checkConstraint(jTextFields, row, constraint, column));
	}

	public boolean subsectionConstraint(MyTextField[][] jTextFields, int row, int column) {
		boolean[] constraint = new boolean[9];
		int subsectionRowStart = (row / 3) * 3;
		int subsectionRowEnd = subsectionRowStart + 3;

		int subsectionColumnStart = (column / 3) * 3;
		int subsectionColumnEnd = subsectionColumnStart + 3;

		for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
			for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
				if (!checkConstraint(jTextFields, r, constraint, c)) {
					// System.out.println("subsectionConstraint FAlse");
					return false;
				}
			}
		}
		// System.out.println("subsectionConstraint True");
		return true;
	}

	boolean checkConstraint(MyTextField[][] jTextFields, int row, boolean[] constraint, int column) {
		if (!jTextFields[row][column].getText().equals("")) {
			if (!constraint[Integer.parseInt(jTextFields[row][column].getText()) - 1]) {
				// System.out.println("chceckContraint");
				constraint[Integer.parseInt(jTextFields[row][column].getText()) - 1] = true;
				// System.out.println("row " + row +" col "+ column);
			} else {
				// System.out.println("chceckContraint FALSE");
				return false;
			}
		}
		// System.out.println("chceckContraint TRUE");
		return true;
	}

	public void printBoard(MyTextField[][] jTextFields) {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				System.out.print(jTextFields[row][column].getText() + " ");
			}
			System.out.println();
		}
	}

	public void Info(JFrame frame2, helpPanel hPanel) {
		frame2.add(hPanel);

		frame2.setVisible(true);

	}

	public void aboutAuthor(JFrame frame3, aboutMe aboutAuthor) {

		frame3.add(aboutAuthor);

		frame3.setVisible(true);

	}
}
