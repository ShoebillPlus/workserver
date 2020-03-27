package com.xr.bgmt.service.impl;

import com.xr.bgmt.service.SysUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysUtilServiceImpl implements SysUtilService {

    private static final Logger logger = LoggerFactory.getLogger(SysUtilServiceImpl.class);

    @Override
    public int hrReg(String username, String password) {
        //如果用户名存在，返回错误

        return 0;
    }
}
