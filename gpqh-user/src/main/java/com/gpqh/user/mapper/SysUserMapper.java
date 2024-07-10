package com.gpqh.user.mapper;


import com.gpqh.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    int save(SysUser sysUser);
    List<String> getAllUserName();

    SysUser getByUsername(String username);
}
