package com.xr.bgmt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xr.bgmt.entity.SysDictItem;
import com.xr.bgmt.DAO.SysDictItemMapper;
import com.xr.bgmt.service.SysDictItemService;
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

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-23
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    private static final Logger logger = LoggerFactory.getLogger(SysDictItemServiceImpl.class);

    @Resource
    SysDictItemMapper sysDictItemMapper;

    @Override
    public IPage<SysDictItem> findPage(Pageable pageable) throws ApiException {
        IPage<SysDictItem> retPage;
        try {
            Page<SysDictItem> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询列表成功");
        } catch (Exception e) {
            logger.error("查询列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysDictItem sysDictItem) throws ApiException {
        try {
        this.save(sysDictItem);
        logger.debug("添加成功" + sysDictItem.getId());
        } catch (ApiException e) {
            logger.error("添加错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除成功" + id);
        } catch (Exception e) {
            logger.error("删除异常", e);
            e.printStackTrace();
            throw new ApiException("删除异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(SysDictItem sysDictItem) throws ApiException {
        try {
            UpdateWrapper<SysDictItem> wrapper = new UpdateWrapper();
            wrapper.eq("id",sysDictItem.getId());
            this.update(sysDictItem,wrapper);
            logger.debug("更新成功" + sysDictItem.getId());
        } catch (ApiException e) {
            logger.error("更新错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<SysDictItem> findByDictCode(String dictCode) throws ApiException {
        List<SysDictItem> sysDictItemList;
        try {
            QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("dict_code",dictCode);
            sysDictItemList = sysDictItemMapper.selectList(queryWrapper);
            logger.debug("查询字典项信息列表成功");
        } catch (Exception e) {
            logger.error("查询字典项信息列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询字典项信息列表异常", HttpStatus.BAD_REQUEST);
        }
        return sysDictItemList;

    }

}
