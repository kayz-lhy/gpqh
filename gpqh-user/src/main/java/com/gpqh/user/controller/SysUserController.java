package com.gpqh.user.controller;


import com.gpqh.common.utils.AjaxResult;
import com.gpqh.user.dto.SysUserDTO;
import com.gpqh.user.service.ISysUserService;
import com.gpqh.user.status.RegisterStatus;
import com.gpqh.user.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class SysUserController {

    @Autowired
    ISysUserService sysUserService;

    // Register
    @PostMapping("/register")
    public AjaxResult register(@RequestBody SysUserDTO sysUserDTO) {
        RegisterStatus status = sysUserService.register(sysUserDTO);
        return AjaxResult.success();
    }
    // Login
    @PostMapping("/login")
    public AjaxResult login(@RequestBody Map<String,String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        String jwt = sysUserService.login(username, password);
        if (jwt != null){
            AjaxResult ajaxResult = AjaxResult.success();
            ajaxResult.put("token", jwt);
            return ajaxResult;
        }
        return AjaxResult.error();
    }
    // ResetPassword

    //
}
