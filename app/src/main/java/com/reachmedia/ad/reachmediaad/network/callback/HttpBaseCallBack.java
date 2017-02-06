package com.reachmedia.ad.reachmediaad.network.callback;

import com.reachmedia.ad.reachmediaad.model.BaseModel;
import com.reachmedia.ad.reachmediaad.utils.LogUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Author:    tedyuen
 * Version    V1.0
 * Date:      16/4/25 下午5:38
 * Description: Http 数据请求 CallBack
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/25          tedyuen             1.0             1.0
 * Why & What is modified:
 */
public class HttpBaseCallBack<T> implements Callback<T> {
    protected String TAG = HttpBaseCallBack.class.getSimpleName();

    @Override
    public void success(T data, Response response) {
        LogUtils.d(TAG, "success--> url = " + response.getUrl());
        if (data instanceof BaseModel) {
            LogUtils.json(TAG, ((BaseModel) data).toJson());
//            LogUtils.d(TAG,"success: "+((BaseModel) data).resdesc);
        }
    }

    @Override
    public void failure(RetrofitError retrofitError) {
        LogUtils.e(TAG, "failure--> url = " + retrofitError.getUrl());
        retrofitError.printStackTrace();
    }
}
