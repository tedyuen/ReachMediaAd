package com.reachmedia.ad.reachmediaad.network.controller;

import com.reachmedia.ad.reachmediaad.app.App;
import com.reachmedia.ad.reachmediaad.model.GetIdModel;
import com.reachmedia.ad.reachmediaad.network.callback.HttpBaseCallBack;
import com.reachmedia.ad.reachmediaad.network.callback.UiDisplayListener;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by tedyuen on 2017/2/7.
 */
public class GetIdController extends BaseHttpController<GetIdModel>{
    public GetIdController(){}

    public GetIdController(UiDisplayListener<GetIdModel> uiDisplayListener){
        super(uiDisplayListener);
    }

    public void getId(){
        getNetData();
    }


    @Override
    protected void getNetData() {

        App.getAppApiService().getId(new HttpBaseCallBack<GetIdModel>(){
            @Override
            public void success(GetIdModel data, Response response) {
                super.success(data, response);
                if (uiDisplayListener != null) {
                    uiDisplayListener.onSuccessDisplay(data);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                super.failure(retrofitError);
                if (uiDisplayListener != null) {
                    uiDisplayListener.onFailDisplay("");
                }
            }
        });
    }
}
