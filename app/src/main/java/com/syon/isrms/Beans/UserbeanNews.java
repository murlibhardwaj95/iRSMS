package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserbeanNews implements Serializable {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("data")
        @Expose
        private List<UserbeandataNews> data = null;
        private final static long serialVersionUID = 861841610621447839L;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<UserbeandataNews> getData() {
            return data;
        }

        public void setData(List<UserbeandataNews> data) {
            this.data = data;
        }

}
