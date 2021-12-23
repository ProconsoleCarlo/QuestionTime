package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import qaCore.MultipleChoiceInterpreter;
import qaCore.QuestionAndAnswers;
import qaCore.TrueOfFalseInterpreter;
import utils.FileLoaderWriter;
/**
 * A test for {@link TrueOfFalseInterpreter}
 * @author Carlo Bobba
 * @version 21/03/2015
 */
public class TrueOrFalseInterpreterTest {
	public static void main(String[] args) {
		FileLoaderWriter fileLoaderWriter = new FileLoaderWriter();
		File file = new File("chapters/Chapther 3 - True Or False.txt");
		ArrayList<String> fileReaded = fileLoaderWriter.load(file);
		TrueOfFalseInterpreter interpreter = new TrueOfFalseInterpreter();
		List<QuestionAndAnswers> questionsList = interpreter.readQuestions(fileReaded);
		for (int i = 0; i < questionsList.size(); i++) {
			System.err.println(questionsList.get(i));
		}
	}
}
