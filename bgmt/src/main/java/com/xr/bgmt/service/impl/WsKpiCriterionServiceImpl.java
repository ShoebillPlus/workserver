package com.xr.bgmt.service.impl;

import com.xr.bgmt.entity.WsKpiCriterion;
import com.xr.bgmt.DAO.WsKpiCriterionMapper;
import com.xr.bgmt.service.WsKpiCriterionService;
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
 * 绩效指标 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-04-07
 */
@Service
public class WsKpiCriterionServiceImpl extends ServiceImpl<WsKpiCriterionMapper, WsKpiCriterion> implements WsKpiCriterionService {

    private static final Logger logger = LoggerFactory.getLogger(WsKpiCriterionServiceImpl.class);

    @Resource
    WsKpiCriterionMapper wsKpiCriterionMapper;

    @Override
    public IPage<WsKpiCriterion> findPage(Pageable pageable) throws ApiException {
        IPage<WsKpiCriterion> retPage;
        try {
            Page<WsKpiCriterion> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询绩效指标列表成功");
        } catch (Exception e) {
            logger.error("查询绩效指标列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询绩效指标列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(WsKpiCriterion wsKpiCriterion) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiCriterion.getId())) {
                wsKpiCriterion.setId(HcUtil.getUUID(true));
            }
        this.save(wsKpiCriterion);
        logger.debug("添加绩效指标成功" + wsKpiCriterion.getId());
        } catch (ApiException e) {
            logger.error("添加绩效指标错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加绩效指标异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加绩效指标异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除绩效指标成功" + id);
        } catch (Exception e) {
            logger.error("删除绩效指标异常", e);
            e.printStackTrace();
            throw new ApiException("删除绩效指标异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(WsKpiCriterion wsKpiCriterion) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiCriterion.getId())) {
                throw new ApiException("编号不能为空", HttpStatus.BAD_REQUEST);
            }
            UpdateWrapper<WsKpiCriterion> wrapper = new UpdateWrapper();
            wrapper.eq("id",wsKpiCriterion.getId());
            this.update(wsKpiCriterion,wrapper);
            logger.debug("更新绩效指标成功" + wsKpiCriterion.getId());
        } catch (ApiException e) {
            logger.error("更新绩效指标错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新绩效指标异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新绩效指标异常", HttpStatus.BAD_REQUEST);
        }
    }

}
