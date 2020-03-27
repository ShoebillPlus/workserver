package com.xr.bgmt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xr.bgmt.entity.WsKpiLevel;
import com.xr.bgmt.DAO.WsKpiLevelMapper;
import com.xr.bgmt.service.WsKpiLevelService;
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
 * 考核等级 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Service
public class WsKpiLevelServiceImpl extends ServiceImpl<WsKpiLevelMapper, WsKpiLevel> implements WsKpiLevelService {

    private static final Logger logger = LoggerFactory.getLogger(WsKpiLevelServiceImpl.class);

    @Resource
    WsKpiLevelMapper wsKpiLevelMapper;

    @Override
    public IPage<WsKpiLevel> findPage(Pageable pageable,String ruleId,String criterionId) throws ApiException {
        IPage<WsKpiLevel> retPage;
        try {
            Page<WsKpiLevel> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            QueryWrapper queryWrapper = new QueryWrapper();
            if(StringUtils.isNotBlank(ruleId)){
                queryWrapper.eq("rule_id", ruleId);
            }
            if(StringUtils.isNotBlank(criterionId)){
                queryWrapper.eq("criterion_id", criterionId);
            }
            retPage = this.page(page,queryWrapper);
            logger.debug("查询考核等级列表成功");
        } catch (Exception e) {
            logger.error("查询考核等级列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询考核等级列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(WsKpiLevel wsKpiLevel) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiLevel.getId())) {
                wsKpiLevel.setId(HcUtil.getUUID(true));
            }
        this.save(wsKpiLevel);
        logger.debug("添加考核等级成功" + wsKpiLevel.getId());
        } catch (ApiException e) {
            logger.error("添加考核等级错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加考核等级异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加考核等级异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除考核等级成功" + id);
        } catch (Exception e) {
            logger.error("删除考核等级异常", e);
            e.printStackTrace();
            throw new ApiException("删除考核等级异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(WsKpiLevel wsKpiLevel) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiLevel.getId())) {
                throw new ApiException("编号不能为空", HttpStatus.BAD_REQUEST);
            }
            UpdateWrapper<WsKpiLevel> wrapper = new UpdateWrapper();
            wrapper.eq("id",wsKpiLevel.getId());
            this.update(wsKpiLevel,wrapper);
            logger.debug("更新考核等级成功" + wsKpiLevel.getId());
        } catch (ApiException e) {
            logger.error("更新考核等级错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新考核等级异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新考核等级异常", HttpStatus.BAD_REQUEST);
        }
    }

}
