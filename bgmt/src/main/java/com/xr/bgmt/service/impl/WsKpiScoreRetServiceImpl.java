package com.xr.bgmt.service.impl;

import com.xr.bgmt.entity.WsKpiScoreRet;
import com.xr.bgmt.DAO.WsKpiScoreRetMapper;
import com.xr.bgmt.service.WsKpiScoreRetService;
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
 * 得分结果 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Service
public class WsKpiScoreRetServiceImpl extends ServiceImpl<WsKpiScoreRetMapper, WsKpiScoreRet> implements WsKpiScoreRetService {

    private static final Logger logger = LoggerFactory.getLogger(WsKpiScoreRetServiceImpl.class);

    @Resource
    WsKpiScoreRetMapper wsKpiScoreRetMapper;

    @Override
    public IPage<WsKpiScoreRet> findPage(Pageable pageable) throws ApiException {
        IPage<WsKpiScoreRet> retPage;
        try {
            Page<WsKpiScoreRet> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询得分结果列表成功");
        } catch (Exception e) {
            logger.error("查询得分结果列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询得分结果列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(WsKpiScoreRet wsKpiScoreRet) throws ApiException {
        try {
            wsKpiScoreRet.setId(HcUtil.getUUID(true));
            this.save(wsKpiScoreRet);
            logger.debug("添加得分结果成功" + wsKpiScoreRet.getId());
        } catch (ApiException e) {
            logger.error("添加得分结果错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加得分结果异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加得分结果异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除得分结果成功" + id);
        } catch (Exception e) {
            logger.error("删除得分结果异常", e);
            e.printStackTrace();
            throw new ApiException("删除得分结果异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(WsKpiScoreRet wsKpiScoreRet) throws ApiException {
        try {
            if (StringUtils.isBlank(wsKpiScoreRet.getId())) {
                throw new ApiException("编号不能为空", HttpStatus.BAD_REQUEST);
            }
            UpdateWrapper<WsKpiScoreRet> wrapper = new UpdateWrapper();
            wrapper.eq("id",wsKpiScoreRet.getId());
            this.update(wsKpiScoreRet,wrapper);
            logger.debug("更新得分结果成功" + wsKpiScoreRet.getId());
        } catch (ApiException e) {
            logger.error("更新得分结果错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新得分结果异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新得分结果异常", HttpStatus.BAD_REQUEST);
        }
    }

}
