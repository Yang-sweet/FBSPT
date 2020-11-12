import org.junit.Test;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.service.judger.TestJudger;

public class HDFSTest {
    @Test
    public void JudgerTest() {
        Submission sub = new Submission("16030140021,2,实时单词计数,E:\\IDEA Projects\\DDSPVSES\\target\\DDSPVSES\\\\tmp\\16030140021-2.jar,1588922099925,2020-05-08 15:14:59,已通过");
        System.out.println("测试实验："+sub.getId()+"-"+sub.getTestID());
        System.out.println("测试结果："+TestJudger.isAccepted(sub));
    }
}
