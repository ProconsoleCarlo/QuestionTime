package qaCore;

import java.util.ArrayList;
import java.util.List;
/**
 * The interpreter for multiple choice questions
 * @author Carlo Bobba
 * @version 30/03/2015
 * @deprecated It is substituted by {@link QuestionsInterpreter}
 */
public class MultipleChoiceInterpreter implements FileInterpreter{
	
	private static String separatorCharacter = "\t";
	private static String correctAnswerLabel = "ANSWER:"+separatorCharacter;
	private static String numberSeparator = ".";
	
	private Settings settings = Settings.getSettings();
	/**
	 * Create a new multiple choice questions interpreter
	 */
	@Deprecated
	public MultipleChoiceInterpreter() {
		super();
	}
	@Deprecated
	public List<QuestionAndAnswers> readQuestions(List<String> questionsList) {
		List<QuestionAndAnswers> questions = new ArrayList<QuestionAndAnswers>();
		int i = 0;
		while (i < questionsList.size()) {
			QuestionAndAnswers multipleChoiceQuestion = new QuestionAndAnswers();
			multipleChoiceQuestion.setQuestionType(settings.getMultipleChoiceType());
			String[] questionParameters = questionsList.get(i).split(separatorCharacter);
			int questionNumber = Integer.parseInt(questionParameters[0].replace(numberSeparator, ""));
			String question = questionParameters[1];
			multipleChoiceQuestion.setQuestion(questionNumber, question);
			i++;
			while (!questionsList.get(i).equals("")) {
				if (questionsList.get(i).startsWith(correctAnswerLabel)) {
					String correctAnswer = questionsList.get(i).replace(correctAnswerLabel, "");
					multipleChoiceQuestion.setCorrectAnswer(correctAnswer);
				} else {
					String[] answerParameters = questionsList.get(i).split(separatorCharacter);
					String answerLetter = answerParameters[0].replace(numberSeparator, "");
					String answer = answerParameters[1];
					multipleChoiceQuestion.addAnswers(answerLetter, answer);
				}
				i++;
			}
			questions.add(multipleChoiceQuestion);
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
