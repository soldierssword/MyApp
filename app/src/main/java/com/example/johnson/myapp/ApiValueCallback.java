package com.example.johnson.myapp;


import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Johnson on 2016/8/29.
 */
public abstract class ApiValueCallback extends Callback<ApiValue> {
    @Override
    public ApiValue parseNetworkResponse(Response response, int i) throws Exception {
        String string=response.body().string();
        ApiValue apiValue = new Gson().fromJson(string,ApiValue.class);
        return apiValue;
    }
}
