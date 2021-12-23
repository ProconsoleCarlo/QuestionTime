package qaCore;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
/**
 * The interpreter of the questions
 * @author Carlo Bobba
 * @version 02/04/2015
 * @since 31/03/2015 (Substitutes {@link MultipleChoiceInterpreter} and {@link TrueOfFalseInterpreter})
 */
public class QuestionsInterpreter implements FileInterpreter{
	
	private ParsingSettings ps = ParsingSettings.getParsingSettings();
	
	private int lastCorrectLine;
	private boolean hasAnswers = false;
	
	private Settings settings = Settings.getSettings();
	/**
	 * Create a new interpreter of questions
	 */
	public QuestionsInterpreter() {
		super();
	}
	
	public List<QuestionAndAnswers> readQuestions(List<String> questionsList) {
		List<QuestionAndAnswers> questions = new ArrayList<QuestionAndAnswers>();
		int i = 0;
		while (i < questionsList.size()) {
			hasAnswers = false;
			QuestionAndAnswers question = new QuestionAndAnswers();
			
			String[] questionParameters = questionsList.get(i).split(ps.separatorCharacter);
			int questionNumber = Integer.parseInt(questionParameters[0].replace(ps.numberSeparator, ""));
			String questionText = questionParameters[1];
			question.setQuestion(questionNumber, questionText);
			i++;
			if (questionsList.get(i).startsWith(ps.referenceLabel)) {
				String referenceImageName = questionsList.get(i).replace(ps.referenceLabel, "");
				try {
					BufferedImage referenceImage = ImageIO.read(new File(settings.getReferencesFolder()+"/"+referenceImageName));
					question.setReferenceImage(referenceImage);
				} catch (IOException e) {
					// TODO problema lettura immagine
					e.printStackTrace();
				}
				i++;
			}
			while (!questionsList.get(i).equals("")) {
				lastCorrectLine = i;
				if (questionsList.get(i).startsWith(ps.correctAnswerLabel)) {
					if (hasAnswers) {
						question.setQuestionType(settings.getMultipleChoiceType());
					} else {
						question.setQuestionType(settings.getTrueOrFalseType());
						question.addAnswers(ps.trueFalseAnswersID[0], ps.trueFalseAnswersID[0]);
						question.addAnswers(ps.trueFalseAnswersID[1], ps.trueFalseAnswersID[1]);
					}
					String correctAnswer = questionsList.get(i).replace(ps.correctAnswerLabel, "");
					question.setCorrectAnswer(correctAnswer);
				} else {
					hasAnswers = true;
					String[] answerParameters = questionsList.get(i).split(ps.separatorCharacter);
					String answerLetter = answerParameters[0].replace(ps.numberSeparator, "");
					String answer = answerParameters[1];
					question.addAnswers(answerLetter, answer);
				}
				i++;
			}
			questions.add(question);
			i++;
		}
		return questions;
	}

	@Override
	public int getLastCorrectLine() {
		return lastCorrectLine;
	}
}
