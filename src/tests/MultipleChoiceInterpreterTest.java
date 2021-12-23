package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import qaCore.MultipleChoiceInterpreter;
import qaCore.QuestionAndAnswers;
import utils.FileLoaderWriter;
/**
 * A test for {@link MultipleChoiceInterpreter}
 * @author Carlo Bobba
 * @version 21/03/2015
 */
public class MultipleChoiceInterpreterTest {
	public static void main(String[] args) {
		FileLoaderWriter fileLoaderWriter = new FileLoaderWriter();
		File file = new File("chapters/Chapther 1 - Multiple Choice.txt");
		ArrayList<String> fileReaded = fileLoaderWriter.load(file);
		MultipleChoiceInterpreter interpreter = new MultipleChoiceInterpreter();
		List<QuestionAndAnswers> questionsList = interpreter.readQuestions(fileReaded);
		for (int i = 0; i < questionsList.size(); i++) {
			System.err.println(questionsList.get(i));
		}
	}
}
