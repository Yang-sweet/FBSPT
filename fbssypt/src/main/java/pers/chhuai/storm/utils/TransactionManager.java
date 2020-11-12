package pers.chhuai.storm.utils;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;

/**
 * 和事务管理相关的工具类
 * 开启事务
 * 提交事务
 * 回滚事务
 * 释放连接
 */
@Repository("txManager")
public class TransactionManager {
    @Resource(name="connectionUtils")
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction() {
        try{
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        try{
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        try{
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        try{
            //还回连接池
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
