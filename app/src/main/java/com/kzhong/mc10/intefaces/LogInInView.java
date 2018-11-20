package com.kzhong.mc10.intefaces;

import com.kzhong.mc10.model.UserAccount;

public interface LogInInView {
    void onResponseSuccess(UserAccount account);
    void onResponseFailure(String error);
}
