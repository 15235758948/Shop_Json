package com.example.administrator.shop_json.details;


import com.hannesdorfmann.mosby.mvp.MvpView;


import com.example.administrator.shop_json.model.GoodsDetail;

import java.util.List;


/**
 * Created by Administrator on 2017/2/16 0016.
 */

public interface GoodsDetailView extends MvpView {

    void showProgress();

    void hideProgress();



    //设置商品信息
    void setData(List <GoodsDetail> data);

    //*商品不存在了*/
    void showError();

    //提示信息
    void showMessage(String msg);

}
