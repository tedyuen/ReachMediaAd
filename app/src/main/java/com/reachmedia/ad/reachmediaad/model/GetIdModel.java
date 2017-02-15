package com.reachmedia.ad.reachmediaad.model;

import android.os.Parcelable;

/**
 * Created by tedyuen on 2017/2/7.
 */

public class GetIdModel extends BaseModel implements Parcelable{


    /**
     * data : {"createTime":1486449675000,"id":1,"macAddress":"2c:f0:ee:0b:22:22","videoId":"11111.mp4"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createTime : 1486449675000
         * id : 1
         * macAddress : 2c:f0:ee:0b:22:22
         * videoId : 11111.mp4
         */

        private long createTime;
        private int id;
        private String mac;
        private String videoId;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }
    }
}
