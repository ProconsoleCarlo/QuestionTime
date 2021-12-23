package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import qaCore.QAManager;
import qaCore.QuestionAndAnswers;
import qaCore.Settings;
import utils.GridBagBuilder;
/**
 * The panel that performs the test, containing questions and answers.
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class TestPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private GridBagBuilder builder = new GridBagBuilder(this);
	
	private Settings settings = Settings.getSettings();
	private QAManager qaManager;
	
	private List<QuestionAndAnswers> questions;
	private JLabel questionLabel;
	private AnswersPanel answersPanel;
	private JButton nextButton;
	private JButton previousButton;
	private ReferencePanel referencePanel;

	private JLabel actualQuestionLabel;
	private int actualQuestion = 0;
	/**
	 * Create a new panel for the test
	 * @param qaManager The manager of the program
	 */
	public TestPanel(QAManager qaManager) {
		super();
		this.qaManager = qaManager;
	}
	
	/**
	 * Create the test
	 * @param questions The test questions
	 */
	public void createTest(List<QuestionAndAnswers> questions) {
		this.questions = questions;
		realize();
	}
	
	private void realize() {
		this.removeAll();
		this.repaint();
		this.revalidate();
			
		JLabel leftFiller = new JLabel();
		builder.add(leftFiller, 0, 0, 1, 1, 1, 0);
		
		actualQuestionLabel = new JLabel();
		actualQuestionLabel.setPreferredSize(new Dimension(settings.getButtonWidth()*2, settings.getButtonHeight()));
		actualQuestionLabel.setMinimumSize(new Dimension(settings.getButtonWidth()*2, settings.getButtonHeight()));
		builder.add(actualQuestionLabel, 1, 0, 2, 1, 0, 0);
		
		JLabel rightFiller = new JLabel();
		builder.add(rightFiller, 3, 0, 1, 1, 1, 0);
		
		questionLabel = new JLabel("");
		questionLabel.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth(), settings.getButtonHeight()));
		builder.add(questionLabel, 0, 1, 4, 1, 1, 0);
		
		answersPanel = new AnswersPanel(this);
		builder.add(answersPanel, 0, 2, 4, 1, 1, 0);
		
		JLabel bottomFiller = new JLabel();
		builder.add(bottomFiller, 0, 3, 4, 1, 1, 1);
		
		JLabel bottomLeftFiller = new JLabel();
		builder.add(bottomLeftFiller, 0, 4, 1, 1, 1, 0);
		
		previousButton = new JButton("Precedente");
		previousButton.setPreferredSize(new Dimension(settings.getButtonWidth()<<1, settings.getButtonHeight()));
		previousButton.setMinimumSize(new Dimension(settings.getButtonWidth()<<1, settings.getButtonHeight()));
		previousButton.setMaximumSize(new Dimension(settings.getButtonWidth()<<1, settings.getButtonHeight()));
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousQuestion();
			}
		});
		builder.add(previousButton, 1, 4, 1, 1, 0, 0);
		
		nextButton = new JButton("Successiva");
		nextButton.setPreferredSize(new Dimension(settings.getButtonWidth()<<1, settings.getButtonHeight()));
		nextButton.setMinimumSize(new Dimension(settings.getButtonWidth()<<1, settings.getButtonHeight()));
		nextButton.setMaximumSize(new Dimension(settings.getButtonWidth()<<1, settings.getButtonHeight()));
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextQuestion();		
			}			
		});
		builder.add(nextButton, 2, 4, 1, 1, 0, 0);
		
		JLabel bottomRightFiller = new JLabel();
		builder.add(bottomRightFiller, 3, 4, 1, 1, 1, 0);
		
		actualQuestion = 0;
		updateQuestion(actualQuestion);
	}
	/**
	 * Advance to the next question
	 */
	protected void nextQuestion() {
		recordUserAnswer();
		if (actualQuestion < questions.size()-1) {
			actualQuestion++;
			updateQuestion(actualQuestion);
		} else {
			qaManager.update();
		}
	}
	
	private void previousQuestion() {
		recordUserAnswer();
		if (actualQuestion > 0) {
			actualQuestion--;
			updateQuestion(actualQuestion);
		}
	}
	
	private void updateQuestion(int value) {
		if (questions.size() != 0) {
			if (actualQuestion == 0) {
				previousButton.setEnabled(false);
			} else {
				previousButton.setEnabled(true);
			}
			if (referencePanel != null) {
				referencePanel.dispose();
			}
			actualQuestionLabel.setText("Domanda "+String.valueOf(actualQuestion+1)+" di "+questions.size());
			questionLabel.setText("<html>"+questions.get(value).getQuestion()+"</html>");
			answersPanel.createAnswers(questions.get(actualQuestion));
			if (questions.get(actualQuestion).getReferenceImage() != null) {
				referencePanel = new ReferencePanel(questions.get(actualQuestion).getReferenceImage());
			}
		}
	}
	
	private void recordUserAnswer() {
		String userAnswer = "";
		try {
			userAnswer = answersPanel.getUserAnswer();
		} catch (NullPointerException npe) {
			userAnswer = "";
		}
		questions.get(actualQuestion).setUserAnswer(userAnswer);
	}
}
