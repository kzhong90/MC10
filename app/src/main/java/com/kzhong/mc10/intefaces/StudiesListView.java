package com.kzhong.mc10.intefaces;

import com.kzhong.mc10.model.Studies;

import java.util.ArrayList;

public interface StudiesListView {
    void onResponseSuccess(ArrayList<Studies> studiesList);
    void onLogOut(String message);
    void onResponseFailure(String error);
}
