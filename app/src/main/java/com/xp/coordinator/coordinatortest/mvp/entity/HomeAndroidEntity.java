package com.xp.coordinator.coordinatortest.mvp.entity;

import com.commonlib.entity.BaseEntity;

import java.util.List;

/**
 * @类描述：首页实体类 android
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/21 0021 15:21
 * @修改人：
 * @修改时间：2017/12/21 0021 15:21
 * @修改备注：
 */

public class HomeAndroidEntity implements BaseEntity {
    private boolean error;
    private List<TypeAndroidEntity> results;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<TypeAndroidEntity> getResults() {
        return results;
    }

    public void setResults(List<TypeAndroidEntity> results) {
        this.results = results;
    }

    public static class TypeAndroidEntity implements BaseEntity {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private String used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
