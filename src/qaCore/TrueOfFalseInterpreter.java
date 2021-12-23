package qaCore;

import java.util.ArrayList;
import java.util.List;
/**
 * The interpreter for true or false questions
 * @author Carlo Bobba
 * @version 30/03/2015
 * @deprecated It is substituted by {@link QuestionsInterpreter}
 */
public class TrueOfFalseInterpreter implements FileInterpreter{
	
	private static String separatorCharacter = "\t";
	private static String correctAnswerLabel = "ANSWER:"+separatorCharacter;
	private static String numberSeparator = ".";
	private static String[] trueFalseAnswersID = {"True", "False"};
	
	private Settings settings = Settings.getSettings();
	/**
	 * Create a new true or false questions interpreter
	 */
	@Deprecated
	public TrueOfFalseInterpreter() {
		super();
	}
	@Deprecated
	public List<QuestionAndAnswers> readQuestions(List<String> questionsList) {
		List<QuestionAndAnswers> questions = new ArrayList<QuestionAndAnswers>();
		int i = 0;
		while (i < questionsList.size()) {
			QuestionAndAnswers trueOrFalseQuestion = new QuestionAndAnswers();
			trueOrFalseQuestion.setQuestionType(settings.getTrueOrFalseType());
			String[] questionParameters = questionsList.get(i).split(separatorCharacter);
			int questionNumber = Integer.parseInt(questionParameters[0].replace(numberSeparator, ""));
			String question = questionParameters[1];
			trueOrFalseQuestion.setQuestion(questionNumber, question);
			trueOrFalseQuestion.addAnswers(trueFalseAnswersID[0], trueFalseAnswersID[0]);
			trueOrFalseQuestion.addAnswers(trueFalseAnswersID[1], trueFalseAnswersID[1]);
			i++;
			String correctAnswer = questionsList.get(i).replace(correctAnswerLabel, "");
			trueOrFalseQuestion.setCorrectAnswer(correctAnswer);
			questions.add(trueOrFalseQuestion);
			i++;
			i++;
		}
		return questions;
	}
	@Override
	public int getLastCorrectLine() {
		// TODO Auto-generated method stub
		return 0;
	}
}
