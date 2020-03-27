package com.xr.bgmt.service.impl;

import com.xr.bgmt.entity.WsKpiRetLevel;
import com.xr.bgmt.DAO.WsKpiRetLevelMapper;
import com.xr.bgmt.service.WsKpiRetLevelService;
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
 * 得分结果等级 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Service
public class WsKpiRetLevelServiceImpl extends ServiceImpl<WsKpiRetLevelMapper, WsKpiRetLevel> implements WsKpiRetLevelService {

    private static final Logger logger = LoggerFactory.getLogger(WsKpiRetLevelServiceImpl.class);

    @Resource
    WsKpiRetLevelMapper wsKpiRetLevelMapper;

    @Override
    public IPage<WsKpiRetLevel> findPage(Pageable pageable) throws ApiException {
        IPage<WsKpiRetLevel> retPage;
        try {
            Page<WsKpiRetLevel> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询得分结果等级列表成功");
        } catch (Exception e) {
            logger.error("查询得分结果等级列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询得分结果等级列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(WsKpiRetLevel wsKpiRetLevel) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiRetLevel.getId())) {
                wsKpiRetLevel.setId(HcUtil.getUUID(true));
            }
        this.save(wsKpiRetLevel);
        logger.debug("添加得分结果等级成功" + wsKpiRetLevel.getId());
        } catch (ApiException e) {
            logger.error("添加得分结果等级错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加得分结果等级异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加得分结果等级异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除得分结果等级成功" + id);
        } catch (Exception e) {
            logger.error("删除得分结果等级异常", e);
            e.printStackTrace();
            throw new ApiException("删除得分结果等级异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(WsKpiRetLevel wsKpiRetLevel) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiRetLevel.getId())) {
                throw new ApiException("编号不能为空", HttpStatus.BAD_REQUEST);
            }
            UpdateWrapper<WsKpiRetLevel> wrapper = new UpdateWrapper();
            wrapper.eq("id",wsKpiRetLevel.getId());
            this.update(wsKpiRetLevel,wrapper);
            logger.debug("更新得分结果等级成功" + wsKpiRetLevel.getId());
        } catch (ApiException e) {
            logger.error("更新得分结果等级错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新得分结果等级异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新得分结果等级异常", HttpStatus.BAD_REQUEST);
        }
    }

}
