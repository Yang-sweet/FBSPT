import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.service.remote.impl.RemoteCommandExecImpl;

public class RemoteExeTest {
    @Test
    public void RemoteExeTest() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        RemoteCommandExecImpl rc = ac.getBean("remoteExecutor", RemoteCommandExecImpl.class);
        //System.out.println(rc.getResultFormHDFS("16030140021", 2));
    }
}
