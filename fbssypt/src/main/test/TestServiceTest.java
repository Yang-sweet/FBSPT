import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import pers.chhuai.storm.service.bean.TestService;

import javax.annotation.Resource;
import java.util.List;
@Repository("TestServiceTest")
public class TestServiceTest {
    @Resource(name="testService")
    TestService ts;
    @Test
    public void findAllTests() {
        List<pers.chhuai.storm.beans.Test> tests = ts.getTests();
        for (pers.chhuai.storm.beans.Test test : tests) {
            System.out.println(test);
        }
    }
}
