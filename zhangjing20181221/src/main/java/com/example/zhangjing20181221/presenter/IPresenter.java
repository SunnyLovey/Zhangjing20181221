package com.example.zhangjing20181221.presenter;

import java.util.Map;

public interface IPresenter {
    void startRequest(String path, Map<String,String> params,Class clazz);
}
