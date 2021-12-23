package tests;

import java.io.File;
import java.util.ArrayList;

import utils.FileLoaderWriter;
/**
 * A test for {@link FileLoaderWriter}
 * @author Carlo Bobba
 * @version 21/03/2015
 */
public class FileLoaderWriterTest {
	public static void main(String[] args) {
		FileLoaderWriter fileLoaderWriter = new FileLoaderWriter();
		File file = new File("chapters/Capitolo1.txt");
		ArrayList<String> fileReaded = fileLoaderWriter.load(file);
		for (int i = 0; i < fileReaded.size(); i++) {
			System.err.println(fileReaded.get(i));
		}	
	}
}
