package pers.chhuai.storm.service.schedule;

import pers.chhuai.storm.beans.Submission;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class TaskList {
	// 提交队列
	public static Queue<Submission> SubmittedQueue = new LinkedList<Submission>();
	// 运行列表
	public static List<Submission> RunningList = new ArrayList<Submission>();
	// 通过列表
	public static List<Submission> FinishedList = new ArrayList<Submission>();
	// 异常列表
	public static List<Submission> ErrorList = new ArrayList<Submission>();
}
