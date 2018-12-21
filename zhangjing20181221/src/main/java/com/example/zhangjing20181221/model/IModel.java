package com.example.zhangjing20181221.model;

import com.example.zhangjing20181221.utils.MyCallBack;

import java.util.Map;

public interface IModel {
    void getData(String path, Map<String,String> params, Class clazz, MyCallBack myCallBack);
}
