package com.example.johnson.myapp;


import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Johnson on 2016/8/29.
 * 此处重写Callback方法,设置回调ApiValue
 */
public abstract class ApiValueCallback extends Callback<ApiValue> {
    @Override
    public ApiValue parseNetworkResponse(Response response, int i) throws Exception {
        String string=response.body().string();
        ApiValue apiValue = new Gson().fromJson(string,ApiValue.class);//json解析器初始化
        return apiValue;
    }
}
