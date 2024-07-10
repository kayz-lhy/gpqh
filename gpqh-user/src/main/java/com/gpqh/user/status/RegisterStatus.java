package com.gpqh.user.status;

import com.gpqh.user.vo.SysUserVO;
import lombok.Data;

@Data
public class RegisterStatus {
    public int code;
    public String message;
    public SysUserVO vo;
}
