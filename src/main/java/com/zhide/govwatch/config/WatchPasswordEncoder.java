package com.zhide.govwatch.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WatchPasswordEncoder
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年04月02日 13:16
 **/
@Component
public class WatchPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.equals(s);
    }
}
