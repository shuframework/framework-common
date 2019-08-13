package com.shuframework.commontools.json;

import java.io.Serializable;

public class RecommendListItemBean implements Serializable {
    private static final long serialVersionUID = 21455356667888L;
 
    private int uid;
    private String nickname;
    private int sex;
    private int age;
    private String avatar;
    private String theme_pic;
    private String job;
    private String signature;
    private String sound;
    private int sound_length;
    private int fee;
 
    public RecommendBean getRecommend() {
        return recommend;
    }
 
    public void setRecommend(RecommendBean recommend) {
        this.recommend = recommend;
    }
 
    private RecommendBean recommend;
 
    public int getUid() {
        return uid;
    }
 
    public void setUid(int uid) {
        this.uid = uid;
    }
 
    public String getNickname() {
        return nickname;
    }
 
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
 
    public int getSex() {
        return sex;
    }
 
    public void setSex(int sex) {
        this.sex = sex;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public String getAvatar() {
        return avatar;
    }
 
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
 
    public String getTheme_pic() {
        return theme_pic;
    }
 
    public void setTheme_pic(String theme_pic) {
        this.theme_pic = theme_pic;
    }
 
    public String getJob() {
        return job;
    }
 
    public void setJob(String job) {
        this.job = job;
    }
 
    public String getSignature() {
        return signature;
    }
 
    public void setSignature(String signature) {
        this.signature = signature;
    }
 
 
    public static class RecommendBean<T> implements Serializable {
        private static final long serialVersionUID = 21455356667889L;
 
        private int id;
        private int user_id;
        private int type;
        private Object content;
        private int zan;
        private int status;
        private String create_time;
        private String update_time;
        private int zanTotal;
        private int commentTotal;
        private T picture;
        private T video;
 
        public int getType() {
            return type;
        }
 
        public void setType(int type) {
            this.type = type;
        }
 
        public int getId() {
            return id;
        }
 
        public void setId(int id) {
            this.id = id;
        }
 
        public int getUser_id() {
            return user_id;
        }
 
        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
 
        public Object getContent() {
            return content;
        }
 
        public void setContent(Object content) {
            this.content = content;
        }
 
        public int getZan() {
            return zan;
        }
 
        public void setZan(int zan) {
            this.zan = zan;
        }
 
        public int getStatus() {
            return status;
        }
 
        public void setStatus(int status) {
            this.status = status;
        }
 
        public String getCreate_time() {
            return create_time;
        }
 
        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
 
        public String getUpdate_time() {
            return update_time;
        }
 
        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
 
        public int getZanTotal() {
            return zanTotal;
        }
 
        public void setZanTotal(int zanTotal) {
            this.zanTotal = zanTotal;
        }
 
        public int getCommentTotal() {
            return commentTotal;
        }
 
        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }
 
        public T getPicture() {
            return picture;
        }
 
        public void setPicture(T picture) {
            this.picture = picture;
        }
 
        public T getVideo() {
            return video;
        }
 
        public void setVideo(T video) {
            this.video = video;
        }
    }
}