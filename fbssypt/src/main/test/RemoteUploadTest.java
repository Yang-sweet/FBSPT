import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.service.remote.impl.RemoteUploadImpl;

public class RemoteUploadTest {
    @Test
    public void remoteUploadTest() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        RemoteUploadImpl rp = ac.getBean("uploader", RemoteUploadImpl.class);
        rp.putFile("C:\\Users\\chhua\\Desktop\\test\\test2.txt", "/usr/");
    }
}
