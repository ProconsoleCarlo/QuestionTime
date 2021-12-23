package qaCore;

import java.util.List;
/**
 * The class that manage the results of a test
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class Results {

	private Settings settings = Settings.getSettings();
	private int nCorrectAnswers;
	private String correctAnswers; 
	private int nBlankAnswers;
	private String blankAnswers;
	private int nErrorAnswers;
	private String errorAnswers;
	
	/**
	 * Create and initialize the class
	 */
	public Results() {
		super();
		initialize();
	}
	
	/**
	 * Get the number of occurrences of a result depending on the requested result.
	 * The requested results are defined in {@link Settings}.
	 * @param requestedResult The type of result we want
	 * @return The number of occurrences of the requested result
	 */
	public int getnResult(String requestedResult) {
		if (requestedResult.equals(settings.getCorrectResultType())) {
			return nCorrectAnswers;
		} else if (requestedResult.equals(settings.getBlankResultType())) {
			return nBlankAnswers;
		} else if (requestedResult.equals(settings.getErrorResultType())) {
			return nErrorAnswers;
		} else {
			return Integer.MAX_VALUE;
		}
	}
	
	/**
	 * Get the results depending on the requested result.
	 * The requested results are defined in {@link Settings}.
	 * @param requestedResult The type of result we want
	 * @return The requested results
	 */
	public String getResult(String requestedResult) {
		if (requestedResult.equals(settings.getCorrectResultType())) {
			return correctAnswers;
		} else if (requestedResult.equals(settings.getBlankResultType())) {
			return blankAnswers;
		} else if (requestedResult.equals(settings.getErrorResultType())) {
			return errorAnswers;
		} else {
			return "";
		}
	}

	/**
	 * Compute the results of the test to obtain the number of correct, blank and erroneous answers.
	 * @param testQuestions The questions of the done test
	 */
	public void computeResults(List<QuestionAndAnswers> testQuestions) {
		initialize();
		for (int i = 0; i < testQuestions.size(); i++) {
			if (testQuestions.get(i).getCorrectAnswer().equals(testQuestions.get(i).getUserAnswer())) {
				correctAnswers = correctAnswers+testQuestions.get(i).toString()+"\n\n";
				nCorrectAnswers++;
			} else if (testQuestions.get(i).getUserAnswer().equals("")) {
				blankAnswers = blankAnswers+testQuestions.get(i).toString()+"\n\n";
				nBlankAnswers++;
			} else {
				errorAnswers = errorAnswers+testQuestions.get(i).toString()+"\n\n";
				nErrorAnswers++;
			}
		}
	}
	
	private void initialize() {
		nCorrectAnswers = 0;
		correctAnswers = "";
		nBlankAnswers = 0;
		blankAnswers = "";
		nErrorAnswers = 0;
		errorAnswers = "";
	}
}
