package com.xr.bgmt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xr.bgmt.entity.SysDept;
import com.xr.bgmt.DAO.SysDeptMapper;
import com.xr.bgmt.service.SysDeptService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构部门 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private static final Logger logger = LoggerFactory.getLogger(SysDeptServiceImpl.class);

    @Resource
    SysDeptMapper sysDeptMapper;

    @Override
    public JSONArray findTree() throws ApiException{
        JSONArray retArray;
        try {
            QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_valid",1);
            queryWrapper.eq("level",1);
            queryWrapper.eq("parent_id",0);
            retArray = JSON.parseArray(JSON.toJSONString(formartnNextTree(sysDeptMapper.selectList(queryWrapper))));
            logger.debug("查询机构部门树形成功");
        } catch (Exception e) {
            logger.error("查询机构部门树形异常", e);
            e.printStackTrace();
            throw new ApiException("查询机构部门树形异常", HttpStatus.BAD_REQUEST);
        }
        return retArray;
    }

    private List<SysDept> formartnNextTree(List<SysDept> sysDeptList){
        for(int i = 0;i<sysDeptList.size();i++){
            QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_valid",1);
            queryWrapper.eq("level",sysDeptList.get(i).getLevel()+1);
            queryWrapper.eq("parent_id",sysDeptList.get(i).getId());
            List<SysDept> sysDepts = sysDeptMapper.selectList(queryWrapper);
            sysDeptList.get(i).setSysDeptList(formartnNextTree(sysDepts));
        }
        return sysDeptList;
    }



    @Override
    public IPage<SysDept> findPage(Pageable pageable) throws ApiException {
        IPage<SysDept> retPage;
        try {
            Page<SysDept> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询机构部门列表成功");
        } catch (Exception e) {
            logger.error("查询机构部门列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询机构部门列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysDept sysDept) throws ApiException {
        try {
        this.save(sysDept);
        logger.debug("添加机构部门成功" + sysDept.getId());
        } catch (ApiException e) {
            logger.error("添加机构部门错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加机构部门异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加机构部门异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除机构部门成功" + id);
        } catch (Exception e) {
            logger.error("删除机构部门异常", e);
            e.printStackTrace();
            throw new ApiException("删除机构部门异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(SysDept sysDept) throws ApiException {
        try {
            UpdateWrapper<SysDept> wrapper = new UpdateWrapper();
            wrapper.eq("id",sysDept.getId());
            this.update(sysDept,wrapper);
            logger.debug("更新机构部门成功" + sysDept.getId());
        } catch (ApiException e) {
            logger.error("更新机构部门错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新机构部门异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新机构部门异常", HttpStatus.BAD_REQUEST);
        }
    }

    public List<SysDept> findDeptsNoMe(Long userId) throws ApiException {
        List<SysDept> sysDeptList;
        try {
            sysDeptList = sysDeptMapper.findDeptsNoMe(userId);
            logger.debug("查询除自己外的部门信息成功");
        } catch (Exception e) {
            logger.error("查询除自己外的部门信息异常", e);
            e.printStackTrace();
            throw new ApiException("查询除自己外的部门信息异常", HttpStatus.BAD_REQUEST);
        }
        return sysDeptList;
    }

}
