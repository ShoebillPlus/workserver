package com.xr.bgmt.service.impl;

import com.xr.bgmt.entity.WsKpiDept;
import com.xr.bgmt.entity.WsKpiScoreRecord;
import com.xr.bgmt.DAO.WsKpiScoreRecordMapper;
import com.xr.bgmt.service.WsKpiScoreRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.xr.bgmt.utils.HcUtil;
/**
 * <p>
 * 得分记录 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Service
public class WsKpiScoreRecordServiceImpl extends ServiceImpl<WsKpiScoreRecordMapper, WsKpiScoreRecord> implements WsKpiScoreRecordService {

    private static final Logger logger = LoggerFactory.getLogger(WsKpiScoreRecordServiceImpl.class);

    @Resource
    WsKpiScoreRecordMapper wsKpiScoreRecordMapper;

    @Override
    public IPage<WsKpiScoreRecord> findPage(Pageable pageable) throws ApiException {
        IPage<WsKpiScoreRecord> retPage;
        try {
            Page<WsKpiScoreRecord> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询得分记录列表成功");
        } catch (Exception e) {
            logger.error("查询得分记录列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询得分记录列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(WsKpiScoreRecord wsKpiScoreRecord) throws ApiException {
        try {
            UpdateWrapper<WsKpiScoreRecord> wrapper = new UpdateWrapper();
            wrapper.eq("assessor_id",wsKpiScoreRecord.getAssessorId());
            wrapper.eq("assessed_id",wsKpiScoreRecord.getAssessedId());
            wrapper.eq("rule_id",wsKpiScoreRecord.getRuleId());
            wrapper.eq("level_id",wsKpiScoreRecord.getLevelId());
            wrapper.eq("month",wsKpiScoreRecord.getMonth());
            wsKpiScoreRecordMapper.delete(wrapper);
            wsKpiScoreRecord.setIsValid(1);
            this.save(wsKpiScoreRecord);
        logger.debug("添加得分记录成功" + wsKpiScoreRecord.getId());
        } catch (ApiException e) {
            logger.error("添加得分记录错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加得分记录异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加得分记录异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除得分记录成功" + id);
        } catch (Exception e) {
            logger.error("删除得分记录异常", e);
            e.printStackTrace();
            throw new ApiException("删除得分记录异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(WsKpiScoreRecord wsKpiScoreRecord) throws ApiException {
        try {
            UpdateWrapper<WsKpiScoreRecord> wrapper = new UpdateWrapper();
            wrapper.eq("id",wsKpiScoreRecord.getId());
            this.update(wsKpiScoreRecord,wrapper);
            logger.debug("更新得分记录成功" + wsKpiScoreRecord.getId());
        } catch (ApiException e) {
            logger.error("更新得分记录错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新得分记录异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新得分记录异常", HttpStatus.BAD_REQUEST);
        }
    }

}
