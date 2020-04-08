package com.xr.bgmt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.bgmt.DAO.SysUserMapper;
import com.xr.bgmt.entity.SysParam;
import com.xr.bgmt.entity.SysUser;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.SysParamService;
import com.xr.bgmt.service.SysUserService;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 人员信息 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    SysUserMapper sysUserMapper;
    private SysParamService sysParamService;

    @Autowired
    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public IPage<SysUserForm> findPage(int type, Pageable pageable)
            throws ApiException {
        IPage<SysUserForm> sysUserFormIPage;
        try {
            Page page = new Page(pageable.getPageNumber(), pageable.getPageSize());
            sysUserFormIPage = this.sysUserMapper.findPage(type, page);

            logger.debug("查询人员信息列表成功");
        } catch (Exception e) {
            logger.error("查询人员信息列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询人员信息列表异常", HttpStatus.BAD_REQUEST);
        }
        return sysUserFormIPage;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void add(SysUser sysUser) throws ApiException {
        try {
            if (StringUtils.isBlank(sysUser.getLoginAccount())) {
                throw new ApiException("登录账号不能为空", HttpStatus.BAD_REQUEST);
            }
            if (findByAccount(sysUser.getLoginAccount()) != null) {
                throw new ApiException("登录账号重复", HttpStatus.BAD_REQUEST);
            }
            // 设置有效
            sysUser.setAccountNonExpired(1);
            sysUser.setCredentialsNonExpired(1);
            sysUser.setAccountNonLocked(1);

            // 设置密码
            BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
            SysParam sysParam = this.sysParamService.findByKey("resetPassword");
            String password = sysUser.getLoginPass() != null ? sysUser.getLoginPass() :
                    sysParam == null ? "111111" : sysParam.getConfigValue();
            sysUser.setLoginPass(bcryptPasswordEncoder.encode(password));
            sysUser.setCreateTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            save(sysUser);
            logger.debug("添加人员信息成功" + sysUser.getId());
        } catch (ApiException e) {
            logger.error("添加人员信息错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加人员信息异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加人员信息异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void delete(String id) throws ApiException {
        try {
            removeById(id);
            logger.debug("删除人员信息成功" + id);
        } catch (Exception e) {
            logger.error("删除人员信息异常", e);
            e.printStackTrace();
            throw new ApiException("删除人员信息异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void refresh(SysUser sysUser) throws ApiException {
        try {
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("id", sysUser.getId());
            sysUser.setUpdateTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            update(sysUser, wrapper);
            logger.debug("更新人员信息成功" + sysUser.getId());
        } catch (ApiException e) {
            logger.error("更新人员信息错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新人员信息异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新人员信息异常", HttpStatus.BAD_REQUEST);
        }
    }

    public SysUser findByAccount(String account) throws ApiException {
        SysUser sysUser;
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("login_account", account);
            sysUser = (SysUser) getOne(queryWrapper);
            logger.debug("查询人员信息成功");
        } catch (Exception e) {
            logger.error("查询人员信息异常", e);
            e.printStackTrace();
            throw new ApiException("查询人员信息异常", HttpStatus.BAD_REQUEST);
        }

        return sysUser;
    }

    public SysUser findByPhone(String phone) throws ApiException {
        SysUser sysUser;
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("phone", phone);
            sysUser = (SysUser) getOne(queryWrapper);
            logger.debug("查询人员信息成功");
        } catch (Exception e) {
            logger.error("查询人员信息异常", e);
            e.printStackTrace();
            throw new ApiException("查询人员信息异常", HttpStatus.BAD_REQUEST);
        }

        return sysUser;
    }

    public SysUser findByOpenid(String openid) throws ApiException {
        SysUser sysUser;
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("openid", openid);
            sysUser = (SysUser) getOne(queryWrapper);
            logger.debug("查询人员信息成功");
        } catch (Exception e) {
            logger.error("查询人员信息异常", e);
            e.printStackTrace();
            throw new ApiException("查询人员信息异常", HttpStatus.BAD_REQUEST);
        }

        return sysUser;
    }

    public List<SysUser> findByMinister(Long id) throws ApiException {
        List<SysUser> sysUserList;
        try {
            sysUserList = sysUserMapper.findByMinister(id);
            logger.debug("查询除董事长外的公司领导信息成功");
        } catch (Exception e) {
            logger.error("查询除董事长外的公司领导信息异常", e);
            e.printStackTrace();
            throw new ApiException("查询除董事长外的公司领导信息异常", HttpStatus.BAD_REQUEST);
        }
        return sysUserList;
    }

    public List<SysUser> findBySameDept(Long id,String month) throws ApiException {
        List<SysUser> sysUserList;
        try {
            sysUserList = sysUserMapper.findBySameDept(id,month);
            logger.debug("查询要评论人信息成功");
        } catch (Exception e) {
            logger.error("查询要评论人信息异常", e);
            e.printStackTrace();
            throw new ApiException("查询要评论人信息异常", HttpStatus.BAD_REQUEST);
        }
        return sysUserList;
    }

    public int checkDept(Long id1,Long id2) throws ApiException {
        int ret;
        try {
            ret = sysUserMapper.checkDept(id1,id2);
            logger.debug("判断人员是否同一部门成功");
        } catch (Exception e) {
            logger.error("判断人员是否同一部门异常", e);
            e.printStackTrace();
            throw new ApiException("判断人员是否同一部门异常", HttpStatus.BAD_REQUEST);
        }
        return ret;
    }
}
