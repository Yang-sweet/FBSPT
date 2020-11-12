import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.service.bean.StaService;

import java.util.List;

public class StatisticsTest {
    @Test
    public void getAllSta() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        StaService ss = ac.getBean("staService", StaService.class);
        List<Statistics> stas = ss.getAllSta();
        for (Statistics sta : stas) {
            System.out.println(sta);
        }
    }
    @Test
    public void getStaByClsId() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        StaService ss = ac.getBean("staService", StaService.class);
        List<Statistics> stas = ss.getStaByClsId("1703011");
        for (Statistics sta : stas) {
            System.out.println(sta);
        }
    }
    @Test
    public void getStaById() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        StaService ss = ac.getBean("staService", StaService.class);
        List<Statistics> stas = ss.getStaById("16030140017");
        for (Statistics sta : stas) {
            System.out.println(sta);
        }
    }
    @Test
    public void updateSta() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        StaService ss = ac.getBean("staService", StaService.class);
        ss.updateSta("16030140020", "2");
    }
}
