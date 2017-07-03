package com.example.administrator.shop_json.details;


import com.example.administrator.shop_json.model.GoodsDetail;
import com.example.administrator.shop_json.model.GoodsDetailResult;
import com.example.administrator.shop_json.network.EasyShopClient;

import com.example.administrator.shop_json.network.UICallBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;


/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class GoodsDetailPresenter extends MvpNullObjectBasePresenter<GoodsDetailView> {

    //获取详情的Call
    private Call getDetailCall;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (getDetailCall != null) getDetailCall.cancel();
    }

    //获取商品的详细数据
    public void getData() {
        getView().showProgress();
        getDetailCall = EasyShopClient.getInstance().getGoodsData();
        getDetailCall.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hideProgress();
                getView().showMessage(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                getView().hideProgress();
                  GoodsDetailResult goodsDetailResult = new Gson().fromJson(body, new TypeToken<GoodsDetailResult>(){}.getType());
//                List<GoodsDetailResult> goodsDetailResult =new Gson().fromJson(body, new TypeToken<List<GoodsDetailResult>>(){}.getType());
//                GoodsDetailResult goodsDetailResult = new Gson().fromJson(body, GoodsDetailResult.class);
                //GoodsDetail goodsDetail=new Gson().fromJson(body,new TypeToken<GoodsDetail>(){}.getType());
                if (goodsDetailResult.getCode() == 1) {
                    //商品详情
                    List<GoodsDetail> goodsDetail = goodsDetailResult.getDatas();
                    getView().setData(goodsDetail);
                } else {
                    getView().showError();
                }

            }

        });
    }
}
