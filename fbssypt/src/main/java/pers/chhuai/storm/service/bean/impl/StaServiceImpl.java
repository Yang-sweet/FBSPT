package pers.chhuai.storm.service.bean.impl;
import org.springframework.stereotype.Service;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.dao.impl.StaDaoImpl;
import pers.chhuai.storm.service.bean.StaService;
import pers.chhuai.storm.utils.TransactionManager;

import javax.annotation.Resource;
import java.util.List;
@Service("staService")
public class StaServiceImpl implements StaService {
    @Resource(name="staDao")
    private StaDaoImpl staDao;
    @Resource(name="txManager")
    private TransactionManager txManager;
    @Override
    public List<Statistics> getAllSta() {
        try {
            txManager.beginTransaction();
            List<Statistics> stas = staDao.getAllSta();
            txManager.commit();
            return stas;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public List<Statistics> getStaById(String id) {
        try {
            txManager.beginTransaction();
            List<Statistics> stas = staDao.getStaById(id);
            txManager.commit();
            return stas;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public List<Statistics> getStaByClsId(String classId) {
        try {
            txManager.beginTransaction();
            List<Statistics> stas = staDao.getStaByClsId(classId);
            txManager.commit();
            return stas;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public void updateSta(String id, String testId) {
        try {
            txManager.beginTransaction();
            staDao.updateSta(id, testId);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
}
