package com.example.zhangjing20181221.presenter;

import com.example.zhangjing20181221.model.IModelmpl;
import com.example.zhangjing20181221.utils.MyCallBack;
import com.example.zhangjing20181221.view.IView;

import java.util.Map;

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private IModelmpl iModelmpl;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        iModelmpl=new IModelmpl();
    }

    @Override
    public void startRequest(String path, Map<String, String> params, Class clazz) {
        iModelmpl.getData(path, params, clazz, new MyCallBack() {
            @Override
            public void requestData(Object data) {
                iView.requestSuccess(data);
            }
        });
    }
    //解除绑定
    public void detachView(){
        if(iModelmpl!=null){
            iModelmpl=null;
        }
        if(iView!=null){
            iView=null;
        }
    }
}
