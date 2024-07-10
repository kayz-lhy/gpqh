package com.gpqh.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUserDTO {
    private int id;
    private String username;
    private String nickname;
    private String password;
    private String role;
}
