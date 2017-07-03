package com.example.administrator.shop_json.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import android.widget.ListView;

import com.example.administrator.shop_json.R;
import com.example.administrator.shop_json.commons.ProgressDialogFragment;
import com.example.administrator.shop_json.model.GoodsDetail;
import com.example.administrator.shop_json.utils.ActivityUtils;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GoodsDetailActivity extends MvpActivity<GoodsDetailView, GoodsDetailPresenter> implements GoodsDetailView {


    @BindView(R.id.listview)
    ListView listView;
    private ActivityUtils activityUtils;
    private ProgressDialogFragment progressDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityUtils = new ActivityUtils(this);


        init();//初始化视图

    }

    //初始化视图
    private void init() {
        presenter.getData();//获取商品详情，业务
    }

    //点击事件，发消息，删除
//    @OnClick({R.id.btn_detail_message,R.id.tv_goods_delete})
//    public void onClick(View view){
//        //判断登录状态
//        if (CachePreferences.getUser().getName() == null){
//            activityUtils.startActivity(LoginActivity.class);
//            return;
//        }
//        switch (view.getId()){
//            //发消息
////            case R.id.btn_detail_message:
////                //跳转环信发消息的页面
////                //根据环信ID判断商品归属，自己不能给自己发消息
////                if (goods_user.getHx_Id().equals(CachePreferences.getUser().getHx_Id())) {
////                    activityUtils.showToast("这个商品是自己发布的哦！");
////                    return;
////                }
////                //跳转到环信的消息页面
////                DefaultLocalUsersRepo.getInstance(this).save(CurrentUser.convert(goods_user));
////                startActivity(HxChatActivity.getStartIntent(GoodsDetailActivity.this,goods_user.getHx_Id()));
////                break;
//            //删除
//            case R.id.tv_goods_delete:
//                //删除相关
//                //弹一个警告，是否删除
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle(R.string.goods_title_delete);
//                builder.setMessage(R.string.goods_info_delete);
//                //设置确认按钮，点击删除
//                builder.setPositiveButton(R.string.goods_delete, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //执行删除方法
//                        presenter.delete(str_uuid);
//                    }
//                });
//                //设置取消按钮
//                builder.setNegativeButton(R.string.popu_cancle,null);
//                builder.create().show();
//                break;
//        }
//    }


    @NonNull
    @Override
    public GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter();
    }

    //左上角返回，需实现的方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }


    //  ########################    视图接口相关方法   #####################
    @Override
    public void showProgress() {
        if (progressDialogFragment == null) progressDialogFragment = new ProgressDialogFragment();
        if (progressDialogFragment.isVisible()) return;
        progressDialogFragment.show(getSupportFragmentManager(), "fragment_progress_dialog");
    }

    @Override
    public void hideProgress() {
        progressDialogFragment.dismiss();
    }



    @Override
    public void setData(List<GoodsDetail> data) {

        GoodsAdapter goodsAdapter = new GoodsAdapter(GoodsDetailActivity.this, data,listView);

        listView.setAdapter(goodsAdapter);

    }




    @Override
    public void showError() {
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

}
