package pers.chhuai.storm.service.judger;

import org.apache.commons.collections.map.HashedMap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class JudgeRules {
//
//	public static boolean isAccepted1(String result, String output) {
//		System.out.println("调用测试算法isAccepted1");
//
//		String[] results = result.split("&\\n");
//		String[] os = result.split(",");
//		for(){}
//
//
//
//
//
//
//		// S1 正则表达式检查格式
//		Pattern p = Pattern.compile(output);
//
//		Map<String ,Integer> m=new HashedMap();
//		String[] os=output.split(",");
//		for(int i=0;i<os.length;i++){
//			//int value=os[i].charAt(os[i].length()-1);
//		}
//
//		String[] results = result.split("&\\n");
//		for (String str : results) {
//			Matcher m = p.matcher(str);
//			if (!m.matches()) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//
//


	public static boolean isAccepted1(String result, String regex) {
		System.out.println("调用测试算法isAccepted1");
		// S1 正则表达式检查格式
		//Pattern.compile函数来实现对指定字符串的截取
		Pattern p = Pattern.compile(regex);
		String[] results = result.split("&\\n");
		for (String str : results) {
			Matcher m = p.matcher(str);
			if (!m.matches()) {
				return false;
			}else {

			}
		}
		return true;
	}

//	public static boolean isAccepted1(String result, String regex) {
//		System.out.println("调用测试算法isAccepted1");
//		// S1 正则表达式检查格式
//		Pattern p = Pattern.compile(regex);
//		String[] results = result.split("&\\n");
//		for (String str : results) {
//			Matcher m = p.matcher(str);
//			if (!m.matches()) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	public static boolean isAccepted2(String result, String regex) {
		// S1 正则表达式检查格式
		Pattern p = Pattern.compile(regex);
		String[] results = result.split("&\\n");
		for (String str : results) {
			Matcher m = p.matcher(str);
			if (!m.matches()) {
				return false;
			}
		}
		// S2 键值对设置判别规则
		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
		for (String str : results) {
			String[] kv = str.split(":");
			if (ht.get(kv[0])==null) {
				ht.put(kv[0], 0);
			} 
			if (Integer.parseInt(kv[1])!=((int)(ht.get(kv[0]))+1)){
				return false;
			} else {
				ht.put(kv[0], Integer.parseInt(kv[1]));
			}
		}
		return true;
	}
	
	public static boolean isAccepted3(String result, String regex) {
		// S1 正则表达式检查格式
		Pattern p = Pattern.compile(regex);
		//String result = txt2String(filePath);
		String[] results = result.split("&\\n");
		System.out.println(result);
		for (String str : results) {
			Matcher m = p.matcher(str);
			if (!m.matches()) {
				System.out.println(str);
				return false;
			}
		}
		// S2 键值对设置判别规则
		List<Double> signal = new ArrayList<Double>();
		Double sum = 0.0;
		Double mean = 0.0;
		Double var = 0.0;
		Double sigma = 0.0;
		for (String str : results) {
			String[] kv = str.split("/");
			signal.add(Double.parseDouble(kv[0].replace("signal=", "")));
			sum += signal.get(signal.size()-1);
			mean = sum/signal.size();
			Double varSum = 0.0;
			for (Double s : signal) {
				varSum += (s-mean)*(s-mean);
			}
			var = varSum / signal.size();
			sigma = Math.sqrt(var);
			// 异常点检测
			if (kv.length==3&&(Double.parseDouble(kv[1].replace("mean=", ""))!=mean||
					Double.parseDouble(kv[2].replace("var=", ""))!=var)) {
					return false;
			} else if (kv.length==2&&(signal.get(signal.size()-1)>mean-3*sigma)&&
					(signal.get(signal.size()-1)<mean+3*sigma)) { //出现异常点Outlier
				return false;
			}
		}
		return true;
	}

	public static boolean isAccepted4(String result, String regex) {
		// S1 正则表达式检查格式
		Pattern p = Pattern.compile(regex);
		//String result = txt2String(filePath);
		String[] results = result.split("&\\n");
		for (String str : results) {
			Matcher m = p.matcher(str);
			if (!m.matches()) {
				System.out.println(str);
				return false;
			}
		}
		return true;
	}
}
