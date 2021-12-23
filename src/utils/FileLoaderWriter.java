package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
/**
 * A class that permit to load/write from/in a text file
 * @author Carlo Bobba
 */
public class FileLoaderWriter {
	
	/**
	 * A method that load a text file and return his lines in an ArrayList<String>.
	 * @param file The file to read
	 * @return The lines of the read file
	 */
	public ArrayList<String> load(File file) {
		ArrayList<String> fileLines = new ArrayList<String>();
		try {
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				fileLines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileLines;
	}
	
	/**
	 * A method to write an ArrayList<String> in a text file
	 * @param fileLines The lines to wrote
	 * @param file The file to wrote
	 * @param append The option of append or not the lines to the end of the text file
	 */
	public void write(List<String> fileLines, File file, boolean append){
		try {
			FileOutputStream fos = new FileOutputStream(file, append);
			PrintStream output = new PrintStream(fos);
			for (int i = 0; i < fileLines.size(); i++) {
				output.println(fileLines.get(i));
			}
			output.close();
		}
		catch (IOException e) {
			System.err.println("Errore: " + e);
		}
	}
	
	/**
	 * A method to append a single line to the end of a text file
	 * @param line The line to append
	 * @param file The file to wrote the line in the end
	 */
	public void write(String line, File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file, true);
			PrintStream output = new PrintStream(fos);
			output.println(line);
			output.close();
		}
		catch (IOException e) {
			System.err.println("Errore: " + e);
		}
	}
}
