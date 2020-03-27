package com.xr.bgmt.service.impl;

import com.xr.bgmt.entity.WsKpiRule;
import com.xr.bgmt.DAO.WsKpiRuleMapper;
import com.xr.bgmt.service.WsKpiRuleService;
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
 * 绩效考核规则 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Service
public class WsKpiRuleServiceImpl extends ServiceImpl<WsKpiRuleMapper, WsKpiRule> implements WsKpiRuleService {

    private static final Logger logger = LoggerFactory.getLogger(WsKpiRuleServiceImpl.class);

    @Resource
    WsKpiRuleMapper wsKpiRuleMapper;

    @Override
    public IPage<WsKpiRule> findPage(Pageable pageable) throws ApiException {
        IPage<WsKpiRule> retPage;
        try {
            Page<WsKpiRule> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询绩效考核规则列表成功");
        } catch (Exception e) {
            logger.error("查询绩效考核规则列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询绩效考核规则列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(WsKpiRule wsKpiRule) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiRule.getId())) {
                wsKpiRule.setId(HcUtil.getUUID(true));
            }
        this.save(wsKpiRule);
        logger.debug("添加绩效考核规则成功" + wsKpiRule.getId());
        } catch (ApiException e) {
            logger.error("添加绩效考核规则错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加绩效考核规则异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加绩效考核规则异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除绩效考核规则成功" + id);
        } catch (Exception e) {
            logger.error("删除绩效考核规则异常", e);
            e.printStackTrace();
            throw new ApiException("删除绩效考核规则异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(WsKpiRule wsKpiRule) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiRule.getId())) {
                throw new ApiException("编号不能为空", HttpStatus.BAD_REQUEST);
            }
            UpdateWrapper<WsKpiRule> wrapper = new UpdateWrapper();
            wrapper.eq("id",wsKpiRule.getId());
            this.update(wsKpiRule,wrapper);
            logger.debug("更新绩效考核规则成功" + wsKpiRule.getId());
        } catch (ApiException e) {
            logger.error("更新绩效考核规则错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新绩效考核规则异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新绩效考核规则异常", HttpStatus.BAD_REQUEST);
        }
    }

}
