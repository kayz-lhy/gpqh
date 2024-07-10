package com.gpqh.user.utils;

import com.gpqh.user.dto.SysUserDTO;
import com.gpqh.user.entity.SysUser;
import com.gpqh.user.vo.SysUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SysUserConverter {

    @Autowired
    PasswordUtil passwordUtil;

    public SysUserDTO dto(SysUser sysUser){
        SysUserDTO dto = new SysUserDTO();
        BeanUtils.copyProperties(sysUser,dto);
        return dto;
    }
    public SysUserDTO dto(SysUserVO sysUserVO){
        SysUserDTO dto = new SysUserDTO();
        BeanUtils.copyProperties(sysUserVO,dto);
        return dto;
    }
    public SysUserVO vo(SysUserDTO dto){
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }
    public SysUserVO vo(SysUser sysUser){
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(sysUser,vo);
        return vo;
    }

    public SysUser registerEntity(SysUserDTO sysUserDTO){
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        sysUser.setPassword(passwordUtil.encodePassword(sysUserDTO.getPassword()));
        sysUser.setCreatedAt(LocalDateTime.now());
        sysUser.setCreatedBy(sysUserDTO.getUsername());
        sysUser.setIsDeleted(false);
        return sysUser;
    }
}
