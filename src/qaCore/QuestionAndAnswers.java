package qaCore;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
/**
 * The class that contains a question, his possible answers, the correct answer and the user answer
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class QuestionAndAnswers {

	private String questionType;
	private int questionNumber;
	private BufferedImage referenceImage;
	private String question;
	private Map<String, String> answers = new HashMap<>();
	private String correctAnswer;
	private String userAnswer = "";
	
	public QuestionAndAnswers() {
		super();
	}
	
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	public void setReferenceImage(BufferedImage referenceImage) {
		this.referenceImage = referenceImage;
	}
	public BufferedImage getReferenceImage() {
		return referenceImage;
	}
	public void setQuestion(int questionNumber, String question) {
		this.questionNumber = questionNumber;
		this.question = question;
	}
	public String getQuestion() {
		return questionNumber+") "+question;
	}
	public void addAnswers(String answerLetter, String answer) {
		answers.put(answerLetter, answer);
	}
	public Map<String, String> getAnswers() {
		return answers;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	
	@Override
	public String toString() {
		String questionAndAnswer = questionNumber+") "+question;
		if (questionType.equals(Settings.getSettings().getMultipleChoiceType())) {
			for (Map.Entry<String, String> entry  : answers.entrySet()) {
				questionAndAnswer += "\n"+entry.getKey()+") "+entry.getValue();
			}
			questionAndAnswer += "\nCorrect is "+correctAnswer+"\nYou said "+userAnswer;
		} else if (questionType.equals(Settings.getSettings().getTrueOrFalseType())) {
			questionAndAnswer += "\nCorrect is "+correctAnswer+"\nYou said "+userAnswer;
		}
		return questionAndAnswer;
	}
}
