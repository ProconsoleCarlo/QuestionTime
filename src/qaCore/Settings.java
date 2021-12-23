package qaCore;

import java.io.File;
import java.util.List;

import utils.FileLoaderWriter;


/**
 * The class that contains the setting of the program
 * @author Carlo Bobba
 * @version 02/04/2015
 */
public class Settings {

	private static Settings settings = new Settings();
	private Settings() {
		super();
	}
	public static Settings getSettings() {
		return settings;
	}
	
	private File testsFolder = new File("Tests");
	private String referencesFolder = testsFolder+"/References";
	public File getTestsFolder() {
		return testsFolder;
	}
	public String getReferencesFolder() {
		return referencesFolder;
	}

	private FileLoaderWriter flw = new FileLoaderWriter();
	private static File settingsFile = new File("settings.dat");
	private static String automaticNextLabel = "automaticNext";
	private static String shuffleLabel = "shuffle";
	
	private List<String> settingsList;
	
	public void initialize() {
		settingsList = flw.load(settingsFile);
		for (int i = 0; i < settingsList.size(); i++) {
			if (settingsList.get(i).startsWith(automaticNextLabel)) {
				automaticNext = Boolean.parseBoolean(settingsList.get(i).split("=")[1]);
			} else if (settingsList.get(i).startsWith(shuffleLabel)) {
				shuffle = Boolean.parseBoolean(settingsList.get(i).split("=")[1]);
			}
		}	
	}
	
	public void updateSettings() {
		for (int i = 0; i < settingsList.size(); i++) {
			if (settingsList.get(i).startsWith(automaticNextLabel)) {
				settingsList.set(i, automaticNextLabel+"="+automaticNext);
			} else if (settingsList.get(i).startsWith(shuffleLabel)) {
				settingsList.set(i, shuffleLabel+"="+shuffle);
			}
		}
		flw.write(settingsList, settingsFile, false);
	}
	
	private String correctResultType = "correct";
	private String blankResultType = "blank";
	private String errorResultType = "error";
	
	public String getCorrectResultType() {
		return correctResultType;
	}
	public String getBlankResultType() {
		return blankResultType;
	}
	public String getErrorResultType() {
		return errorResultType;
	}

	/*
	 * Settings of the file containing the test to be correctly represented
	 */
	private String multipleChoiceType = "MultipleChoice";
	private String trueOrFalseType = "TrueOrFalse";
	public String getMultipleChoiceType() {
		return multipleChoiceType;
	}
	public String getTrueOrFalseType() {
		return trueOrFalseType;
	}
	
	/*
	 * Settings of the test
	 */
	private boolean automaticNext = false;
	private boolean shuffle = true;
	public void setAutomaticNext(boolean automaticNext) {
		this.automaticNext = automaticNext;
	}
	public boolean isAutomaticNext() {
		return automaticNext;
	}
	public void setShuffle(boolean shuffle) {
		this.shuffle = shuffle;
	}
	public boolean isShuffle() {
		return shuffle;
	}
	
	/*
	 * Settings of the GUI
	 */
	private int buttonWidth = 60;
	private int buttonHeight = 60;
	public int getButtonWidth() {
		return buttonWidth;
	}
	public int getButtonHeight() {
		return buttonHeight;
	}
}
