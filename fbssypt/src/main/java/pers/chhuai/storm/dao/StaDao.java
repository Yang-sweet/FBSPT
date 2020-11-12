package pers.chhuai.storm.dao;

import org.springframework.stereotype.Repository;
import pers.chhuai.storm.beans.Statistics;

import java.util.List;

public interface StaDao {
    /**
     * 获取所有统计信息
     */
    public List<Statistics> getAllSta();

    /**
     * 根据id获取统计信息
     * @param id
     */
    public List<Statistics> getStaById(String id);

    /**
     * 根据班级号获取统计信息
     * @param classId
     */
    public List<Statistics> getStaByClsId(String classId);

    /**
     * 更新统计情况，任务通过时更新
     * @param id
     * @param testId
     */
    public void updateSta(String id, String testId);

}
