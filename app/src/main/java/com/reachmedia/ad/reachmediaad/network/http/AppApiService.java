package com.reachmedia.ad.reachmediaad.network.http;

import com.reachmedia.ad.reachmediaad.app.AppApiContact;
import com.reachmedia.ad.reachmediaad.model.GetIdModel;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by tedyuen on 2017/2/6.
 */

public interface AppApiService {


    @FormUrlEncoded
    @POST(AppApiContact.API_GET_ID)
    void getId(@Field("screenMac") String screenMac,Callback<GetIdModel> cb);
}
