package qaCore;

import java.util.List;
/**
 * The generic test files interpreter
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public interface FileInterpreter {
	/**
	 * Read the test file's lines and create the test
	 * @param questionsList The lines of the file containing the test
	 * @return The questions of the test
	 */
	public List<QuestionAndAnswers> readQuestions(List<String> questionsList);
	/**
	 * @return the last correct line interpreted
	 */
	public int getLastCorrectLine();
	
}
