package com.example.administrator.shop_json.details;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.shop_json.R;
import com.example.administrator.shop_json.model.GoodsDetail;

import java.util.List;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;


/**
 * Created by Administrator on 2017/6/19.
 */

public class GoodsAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private List<GoodsDetail> list;
    private Context context;
    private ImageLoader mImageLoader;
    private int mStart, mEnd;//listview屏幕可视范围内的第一条item和最后一个item
    private boolean mFirstIn;//判断是否是第一次启动程序

    public static String URLS[];//设置一个数组，用来保存所有图片的URL

    public GoodsAdapter(GoodsDetailActivity goodsDetailActivity, List<GoodsDetail> data, ListView listView) {
        this.context = goodsDetailActivity;
        this.list = data;
        mImageLoader = new ImageLoader(listView);
        URLS = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            URLS[i] = data.get(i).getPicSmall();//将data中的每一个URL赋值给数组
        }
        Log.e("GoodsAdapter: 11111111", URLS + "");
        listView.setOnScrollListener(this);
        mFirstIn = true;//写在构造函数中，第一次调用newsAdapter时表示第一次启动程序，显示listview

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;

        if (convertView == null) {
            viewHoder = new ViewHoder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_goods_detail, null);
            viewHoder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_detail_name);
            viewHoder.tv_person = (TextView) convertView.findViewById(R.id.tv_detail_describe);
            viewHoder.imageView = (ImageView) convertView.findViewById(R.id.tv_detail_pic);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }


        viewHoder.tv_item_name.setText(list.get(position).getName());
        viewHoder.tv_person.setText(list.get(position).getDescription());


        String urlss = list.get(position).getPicSmall();
        viewHoder.imageView.setTag(urlss);//给ImageView设置标志，即对应的图片网址
        mImageLoader.showImages(viewHoder.imageView, urlss);


        return convertView;
    }

    /*
        * 当listview滑动状态变化时调用
        */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {//listview停止滚动
            //加载可见项

            mImageLoader.loadImages(mStart, mEnd);
            Log.e("onScrollStateChanged:2 ", mImageLoader + "");
        } else {
            //停止加载任务
            mImageLoader.cancelAllTasks();
        }

    }

    /*
         * 当listview滑动的时候调用
         */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = mStart + visibleItemCount;
        //只在第一次加载的时候调用
        if (mFirstIn && visibleItemCount > 0) {//表示第一次加载listview并且已经绘制了可见范围内的item
            mImageLoader.loadImages(mStart, mEnd);
            mFirstIn = false;//加载图片后即设为false
        }
    }

    class ViewHoder {
        TextView tv_item_name;
        TextView tv_person;
        ImageView imageView;

    }

}
