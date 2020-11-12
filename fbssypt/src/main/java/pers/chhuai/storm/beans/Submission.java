package pers.chhuai.storm.beans;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.service.bean.SubmissionService;
public class Submission {
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static SubmissionService ss = ac.getBean("submissionService", SubmissionService.class);
	private String id;

	public Submission() {}

	private String testID;
	private String testName;
	private String URI;
	private long timestamp;
	private String date;
	private String state;
	private String result;
	private String jobname;
	@Override
	public String toString() {
		return id+","+testID+","+testName+","+URI+","+timestamp+","+date+","+state+","+jobname;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public Submission(String id, String testID, String testName, String uRI, long timestamp, String date, String state, String result, String jobname) {
		this.id = id;
		this.testID = testID;
		this.testName = testName;
		URI = uRI;
		this.timestamp = timestamp;
		this.date = date;
		this.state = state;
		this.result = result;
		this.jobname = jobname;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTestID() {
		return testID;
	}

	public void setTestID(String testID) {
		this.testID = testID;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Submission(String sub_info) {
		String str[] = sub_info.split(",");
		this.id =str[0];
		this.testID = str[1];
		this.testName = str[2];
		this.URI = str[3];
		this.timestamp =  Long.valueOf(str[4]);
		this.date = str[5];
		this.state = str[6];
		this.jobname = str[7];
	}
	
	public void updateState(String state, String result) {
		this.setState(state);
		ss.updateState(this, state, result);
	}
	
	public void updateState(String state) {
		this.setState(state);
		ss.updateState(this, state);
	}
}
