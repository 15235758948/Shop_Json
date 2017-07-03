package com.example.administrator.shop_json.model;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 商品展示详情类
 * "id": 1,
 * "name": "Tony\u8001\u5e08\u804ashell\u2014\u2014\u73af\u5883\u53d8\u91cf\u914d\u7f6e\u6587\u4ef6",
 * "picSmall": "http:\/\/img.mukewang.com\/55237dcc0001128c06000338-300-170.jpg",
 * "picBig": "http:\/\/img.mukewang.com\/55237dcc0001128c06000338.jpg",
 * "description": "\u4e3a\u4f60\u5e26\u6765shell\u4e2d\u7684\u73af\u5883\u53d8\u91cf\u914d\u7f6e\u6587\u4ef6",
 * "learner": 12312
 */
@SuppressWarnings("unused")
public class GoodsDetail implements Serializable{

    /*名称*/
    @SerializedName("name")
    private String name;
    /*商品描述*/
    @SerializedName("description")
    private String description;
    /*发布者*/
    @SerializedName("learner")
    private String learner;
    /*商品图片uri*/
    @SerializedName("picSmall")
    private String picSmall;

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLearner() {
        return learner;
    }

    public void setLearner(String learner) {
        this.learner = learner;
    }

}
