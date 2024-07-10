package com.gpqh.user.service;

import com.gpqh.user.dto.SysUserDTO;
import com.gpqh.user.status.RegisterStatus;

public interface ISysUserService {
    Boolean verifyUsername(String username);
    RegisterStatus register(SysUserDTO sysUserDTO);

    String login(String username, String password);
    String authenticate(String username, String password);
}
