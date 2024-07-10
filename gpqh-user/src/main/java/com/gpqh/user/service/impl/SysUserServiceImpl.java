package com.gpqh.user.service.impl;

import com.gpqh.common.utils.AjaxResult;
import com.gpqh.user.dto.SysUserDTO;
import com.gpqh.user.entity.SysUser;
import com.gpqh.user.mapper.SysUserMapper;
import com.gpqh.user.service.ISysUserService;
import com.gpqh.user.status.RegisterStatus;
import com.gpqh.user.utils.JwtUtil;
import com.gpqh.user.utils.PasswordUtil;
import com.gpqh.user.utils.SysUserConverter;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    private SysUserConverter converter;
    @Autowired
    private PasswordUtil passwordUtil;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Boolean verifyUsername(String username) {
        List<String> list = sysUserMapper.getAllUserName();
        for (String name : list) {
            if (username.equals(name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public RegisterStatus register(SysUserDTO sysUserDTO) {
        // 检查重名
        RegisterStatus registerStatus = new RegisterStatus();

        SysUser sysUser = converter.registerEntity(sysUserDTO);
        int rows = sysUserMapper.save(sysUser);
        if (rows > 0) {
            registerStatus.setCode(1);
            registerStatus.setMessage("注册成功");
            registerStatus.setVo(converter.vo(sysUser));
        } else {
            registerStatus.setCode(0);
            registerStatus.setMessage("注册失败");
            registerStatus.setVo(null);
        }
        return registerStatus;
    }

    @Override
    public String login(String username, String password) {
        return authenticate(username, password);
    }

    @Override
    public String authenticate(String username, String password) {
        SysUser user = sysUserMapper.getByUsername(username);
        if (user != null && passwordUtil.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        }else {
            return null;
        }
    }
}
