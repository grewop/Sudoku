package sudoku.project;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.prefs.Preferences;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SudokuModel {
private int[][] solvedArray = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
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
	public void saveGame(int[][] iTextFields) throws IOException {
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		f.setCurrentDirectory(new File("/D:/"));// zaczynamy na D

		int value = f.showOpenDialog(null);
		StringBuilder builder = new StringBuilder();

		if (value == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "You closed without selecting a file");
			return;
		}
		for (int i = 0; i < iTextFields.length; i++)// rzad
		{
			for (int j = 0; j < iTextFields.length; j++)// kolumna
			{
				builder.append(iTextFields[i][j] + "");// dodwanai do stringa wyjscia
				if (j < iTextFields.length - 1)// dodaj przecinek do konca jesli to nie koniec
					builder.append(",");
			}
			builder.append("\n");// dodaj linie na koncu
		}
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(f.getCurrentDirectory() + f.getSelectedFile().getName() + ".txt"));
		
		writer.write(builder.toString());
		writer.close();
	}

	public int[][] loadGame(int[][] iTextFields) throws NumberFormatException, IOException {
		Preferences pref = Preferences.userRoot().node(getClass().getName());

		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		f.setCurrentDirectory(new File("/D:/"));
		int value = f.showOpenDialog(null);

		if (value == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "You closed without selecting a file");
			return iTextFields;
		}

		File savedGameFile = f.getSelectedFile();
		int[][] board = new int[9][9];
		BufferedReader reader = new BufferedReader(new FileReader(savedGameFile));
		String line = "";
		int row = 0;
		while ((line = reader.readLine()) != null) {
			String[] cols = line.split(",");
			int col = 0;
			for (String c : cols) {
				if(c.equals("")) {
					iTextFields[row][col] = 0;
				}else {
				iTextFields[row][col] = Integer.parseInt(c);
				}
				col++;

			}
			row++;
		}
		reader.close();
		return iTextFields;
	}

	public int[][] clearGrid(int[][] iTextFields) {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				iTextFields[x][y] = 0;

			}
		}
		return iTextFields;
	}

	public int[][] finishGame( int[][] iTextFields) {
		int sumALL = 0;
		int sumRow[] = new int[9];
		int sumCol[] = new int[9];
		int sum3x3[][] = new int[3][3];

		for (int row = 0; row < 9; row++) {

			for (int col = 0; col < 9; col++) {
				// sprawdza wszystkie pola
				if (iTextFields[row][col] == 0) {
					JOptionPane.showMessageDialog(null, "Dokończ grę");
					
					return iTextFields;
				}
				sumALL = sumALL + iTextFields[row][col];
				// sprawdzaj rzedy
				sumRow[row] += iTextFields[row][col];

			}
		}
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				if (iTextFields[row][col] == 0) {
					JOptionPane.showMessageDialog(null, "Dokończ grę");
					return iTextFields;
				}
				// sprawdz kolumny
				sumCol[col] += iTextFields[row][col];
			}
		}
		for (int a = 0; a < 3; ++a) {
			for (int b = 0; b < 3; ++b) {

				for (int i = 0; i < 3; ++i) {
					for (int j = 0; j < 3; ++j) {

						sum3x3[a][b] += iTextFields[((a * 3) + i)][((b * 3) + j)];

					}

				}
				
			}
		}
		// zamiana tablicy 2d na 1d dla łatwiejszego sprawdzania
		int[] cubes = Stream.of(sum3x3).flatMapToInt(IntStream::of).toArray();

		// Sprawdzenie ostatnie
		for (int i = 0; i < 9; i++) {
			int box = cubes[i];
			int z = sumCol[i];
			int y = sumRow[i];

			

			if (z != 45 || y != 45 || box != 45) {

				JOptionPane.showMessageDialog(null, "Gdzieś jest błąd");
				break;
			}
			if (i == 8 && sumALL == 405) { // i == 8 oznacza ze przeszlo bez bledow przez wszystko
				JOptionPane.showMessageDialog(null, "Gratulacje rozwiązałeś sudoku");

			}
		}
		return iTextFields;

	}
	

// działa ale moze bardzo długo 
	
	public boolean solve(int[][] iTextFields, int[][] solvedArray )   {
		
		this.solvedArray =  solvedArray;
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				if (iTextFields[row][column] == 0) {
					for (int k = 1; k <= 9; k++) {

						
						iTextFields[row][column] = k;
						
						if (isValid(iTextFields, row, column) && solve(iTextFields, solvedArray)) {
							solvedArray[row][column] = k;
							return true;

						}
						iTextFields[row][column] = 0;
						solvedArray[row][column] = 0;
					}
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean isValid(int[][] iTextFields, int row, int column) {
		
		return (rowConstraint(iTextFields, row) && columnConstraint(iTextFields, column)
				&& subsectionConstraint(iTextFields, row, column));
	}

	public boolean rowConstraint(int[][] iTextFields, int row) {
		boolean[] constraint = new boolean[9];
		
		return IntStream.range(0, 9).allMatch(column -> checkConstraint(iTextFields, row, constraint, column));
	}

	public boolean columnConstraint(int[][] iTextFields, int column) {
		boolean[] constraint = new boolean[9];
		
		return IntStream.range(0, 9).allMatch(row -> checkConstraint(iTextFields, row, constraint, column));
	}

	public boolean subsectionConstraint(int[][] iTextFields, int row, int column) {
		boolean[] constraint = new boolean[9];
		int subsectionRowStart = (row / 3) * 3;
		int subsectionRowEnd = subsectionRowStart + 3;

		int subsectionColumnStart = (column / 3) * 3;
		int subsectionColumnEnd = subsectionColumnStart + 3;

		for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
			for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
				if (!checkConstraint(iTextFields, r, constraint, c)) {
				
					return false;
				}
			}
		}
		
		return true;
	}

	boolean checkConstraint(int[][] iTextFields, int row, boolean[] constraint, int column) {
		if (iTextFields[row][column] != 0) {
			if (!constraint[iTextFields[row][column] - 1]) {
				
				constraint[iTextFields[row][column] - 1] = true;
			
			} else {
			
				return false;
			}
		}
		
		return true;
	}

	public void printBoard(int[][] iTextFields) {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				System.out.print(iTextFields[row][column] + " ");
				
				
			}
			System.out.println();
		}
	}
public int[][] getReturnSolve(){
		return solvedArray;
	}

	public int[][] loadGameField(int[][] iTextFields, int[][] gameField) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int temp = gameField[i][j];
				if(temp == 0) {
					iTextFields[i][j] = 0;
				}else {
				iTextFields[i][j] = gameField[i][j];
				}
					
				
			}
		}
		return iTextFields;
	}

}		

	

