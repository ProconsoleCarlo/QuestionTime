package qaCore;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.JOptionPane;

import utils.FileLoaderWriter;
/**
 * The class that manage the program
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class QAManager extends Observable{
		
	private FileLoaderWriter flw = new FileLoaderWriter();
	private Settings settings = Settings.getSettings();
	private Results results = new Results();
	
	private FileInterpreter actualInterpreter;
	private List<QuestionAndAnswers> questions;

	private boolean testRunning = false;

	private Map<String, File> availableTests = new HashMap<String, File>();
	private List<String> testsFilesNames = new ArrayList<String>();
	
	/**
	 * Create a new manager of the program
	 * @param actualInterpreter The available interpreters
	 */
	public QAManager(FileInterpreter actualInterpreter) {
		super();
		this.actualInterpreter = actualInterpreter;
		initialize();
	}
	
	public Results getResults() {
		return results;
	}
	public String[] getAvailableTests() {
		String[] testsFilesNamesArray = (String[])testsFilesNames.toArray(new String[testsFilesNames.size()]);
		Arrays.sort(testsFilesNamesArray);
		return testsFilesNamesArray;
	}
	public boolean isTestRunning() {
		return testRunning;
	}
	
	private void initialize() {
		File[] chaptersFiles = settings.getTestsFolder().listFiles();
		for (int i = 0; i < chaptersFiles.length; i++) {
			if (chaptersFiles[i].isFile()) {
				String chaptherFileName = chaptersFiles[i].getName().substring(0, chaptersFiles[i].getName().lastIndexOf("."));
				availableTests.put(chaptherFileName, chaptersFiles[i]);
				testsFilesNames.add(chaptherFileName);
			}	
		}
	}
	
	/**
	 * Create the test questions and answers
	 * @param selectedTest The file that contains the questions
	 * @return The test questions
	 */
	public List<QuestionAndAnswers> createTest(String selectedTest) {
		File questionsFile = availableTests.get(selectedTest);
		if (questions != null) {
			questions.clear();
		}
		List<String> questionsFileLines = flw.load(questionsFile);
		try {
			questions = actualInterpreter.readQuestions(questionsFileLines);
			testRunning = true;
		} catch (Exception e) {
			testRunning = false;
			JOptionPane.showMessageDialog(null, "<html>Il file "+questionsFile+" non è formattato correttamente!" + 
												"<br />" +
												"Il problema è dopo la riga "+actualInterpreter.getLastCorrectLine() +
												".</html>");
		}
		if (settings.isShuffle()) {
			Collections.shuffle(questions);
		}
		return questions;
	}
	
	/**
	 * Terminate the execution of the actual test
	 */
	public void terminateTest() {
		testRunning = false;
		results.computeResults(questions);
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}
}
