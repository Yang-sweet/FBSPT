import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.service.bean.SubmissionService;

import java.util.List;

public class SubmissionServiceTest {
    @Test
    public void findAllSubmissions() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        SubmissionService ss = ac.getBean("submissionService", SubmissionService.class);
        List<Submission> subs = ss.getSubmissions();
        for (Submission sub : subs) {
            System.out.println(sub);
        }
    }
    @Test
    public void findSubmissionById() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        SubmissionService ss = ac.getBean("submissionService", SubmissionService.class);
        List<Submission> subs = ss.getSubmissions("16030140021");
        for (Submission sub : subs) {
            System.out.println(sub);
        }
    }
    @Test
    public void getSubResults() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        SubmissionService ss = ac.getBean("submissionService", SubmissionService.class);
        List<Statistics> results = ss.getSubResults();
        for (Statistics result : results) {
            System.out.println(result);
        }
    }
    @Test
    public void getSubResultsById() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        SubmissionService ss = ac.getBean("submissionService", SubmissionService.class);
        List<Statistics> results = ss.getSubResults("1603014");
        for (Statistics result : results) {
            System.out.println(result);
        }
    }
}
