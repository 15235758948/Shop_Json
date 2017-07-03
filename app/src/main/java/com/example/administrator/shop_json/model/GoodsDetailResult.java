package com.example.administrator.shop_json.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 商品详情的实体类
 * {
 "status": 1,
 "data": [{
 *
 */

public class GoodsDetailResult {

    private int status;

    @SerializedName("data")
    private List<GoodsDetail>  datas;

    public List<GoodsDetail> getDatas() {
        return datas;
    }

    public void setDatas(List<GoodsDetail>  datas) {
        this.datas = datas;
    }

    public int getCode() {
        return status;
    }

    public void setCode() {
        this.status = 1;
    }

}
