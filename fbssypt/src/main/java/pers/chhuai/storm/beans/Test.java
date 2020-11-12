package pers.chhuai.storm.beans;

public class Test {
	private String testId = null;
	private String title = null;
	private String introduction = null;
	private String input = null;
	private String output = null;
	private String url = null;
	private String regex = null;

	public Test(String testId, String title, String introduction, String input, String output, String url, String regex) {
		this.testId = testId;
		this.title = title;
		this.introduction = introduction;
		this.input = input;
		this.output = output;
		this.url = url;
		this.regex = regex;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public String toString() {
		return "Test{" +
				"testId='" + testId + '\'' +
				", title='" + title + '\'' +
				", introduction='" + introduction + '\'' +
				", input='" + input + '\'' +
				", output='" + output + '\'' +
				", url='" + url + '\'' +
				", regex='" + regex + '\'' +
				'}';
	}

	public Test() {}
}
