package pers.chhuai.storm.utils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;


/**
 * 连接的工具类，用于从数据源中获取一个连接，并实现和线程的绑定
 */
@Repository("connectionUtils")
public class ConnectionUtils {
    private ThreadLocal<Connection> tl =new ThreadLocal<Connection>();
    @Resource(name="dataSource")
    private DataSource dataSource;


    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection() {
        try {
            //1.先从ThreadLocal上获取
            Connection conn = tl.get();
            //2.判断当前线程上是否有连接
            if(conn==null) {
                //3.从数据源中获取一个连接，并存入ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        tl.remove();
    }
}
