package pers.chhuai.storm.service.judger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.beans.Test;
import pers.chhuai.storm.service.bean.TestService;
import pers.chhuai.storm.service.remote.impl.RemoteCommandExecImpl;
import pers.chhuai.storm.service.schedule.TimeDelay;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;


public class TestJudger {
	// 1.获取容器
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// 2.获取业务层对象
	private static TestService ts = ac.getBean("testService", TestService.class);
	private static RemoteCommandExecImpl rc = ac.getBean("remoteExecutor", RemoteCommandExecImpl.class);

//	public static boolean isAccepted(Submission sub) {
//		System.out.println("==========调用判决器==========");
//		List<Test> tests = ts.getTests();
//		String regex = tests.get(Integer.parseInt(sub.getTestID())-1).getRegex();
//		try {
//			String result = rc.getResultFormHDFS(sub);
//			TimeDelay.delay(1);
//			return isAccepted(sub.getTestID(), result, regex);
//		}catch(Exception e) {
//
//			e.printStackTrace();
//		}
//
//		return false;
//	}


//	public static boolean isAccepted(String testId, String result,
//		String regex) throws Exception {
//		String path = "pers.chhuai.storm.service.judger.JudgeRules";
//		try {
//			Class judger = Class.forName(path); //java反射 获取类
//			// 按名称获取方法
//			Method m = judger.getDeclaredMethod("isAccepted" + testId,   //获取方法
//				String.class, String.class);
//			return (Boolean)m.invoke(judger, result, regex);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    	return false;
//	}

	public static boolean isAccepted(Submission sub) {
		System.out.println("==========调用判决器==========");
		List<Test> tests = ts.getTests();
		String output = tests.get(Integer.parseInt(sub.getTestID())-1).getOutput();
		String regex = tests.get(Integer.parseInt(sub.getTestID())-1).getRegex();

		try {
			String result = rc.getResultFormHDFS(sub);
			System.out.println("hdfs result="+result);
			TimeDelay.delay(1);
//			return isAccepted(sub.getTestID(), result, output);
			return isAccepted(sub.getTestID(), result,regex);
		}catch(Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public static boolean isAccepted(String testId, String result,
									 String regex) throws Exception {
		String path = "pers.chhuai.storm.service.judger.JudgeRules";
		try {
			Class judger = Class.forName(path); //java反射 获取类
			// 按名称获取方法
			Method m = judger.getDeclaredMethod("isAccepted" + testId,   //获取方法
					String.class, String.class);
			return (Boolean)m.invoke(judger, result, regex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
















}
