package qaCore;
/**
 * The settings of the parser
 * @author Carlo Bobba
 * @vesion 02/04/2015
 */
public class ParsingSettings {

	private static ParsingSettings parsingSettings = new ParsingSettings();
	private ParsingSettings() {
		super();
	}
	public static ParsingSettings getParsingSettings() {
		return parsingSettings;
	}
	
	protected String separatorCharacter = "\t";
	protected String correctAnswerLabel = "ANSWER:"+separatorCharacter;
	protected String referenceLabel = "REFERENCE:"+separatorCharacter;
	protected String numberSeparator = ".";
	protected String[] trueFalseAnswersID = {"True", "False"};
}
