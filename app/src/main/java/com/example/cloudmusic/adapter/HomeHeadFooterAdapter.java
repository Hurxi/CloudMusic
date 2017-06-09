package com.example.cloudmusic.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cloudmusic.R;
import com.example.cloudmusic.bean.Home;
import com.example.cloudmusic.bean.HomeResponse;

import java.util.ArrayList;

import image.SmartImageView;

/**
 * 固定头尾Adapter
 */
public class HomeHeadFooterAdapter extends RecyclerView.Adapter {
    private static final int TYPE_HEAD = 0;//头布局
    private static final int TYPE_FOOTER = 1;//尾布局
    private static final int TYPE_NORMAL = 2;//默认的布局


    ArrayList<Home> homes;

    private View mHeadView;
    private View mFooterView;

    public HomeHeadFooterAdapter(ArrayList<Home> homes) {
        this.homes = homes;
    }

    /**
     * 设置头部的View
     *
     * @param view 想要显示的View
     */
    public void setHeadView(View view) {
        mHeadView = view;
    }

    /**
     * 设置尾部的View
     *
     * @param view 想要显示的View
     */
    public void setFooterView(View view) {
        mFooterView = view;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeadView != null && position == 0) {
            return TYPE_HEAD;
        }
        if (mFooterView != null && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断当前是否应该设置头布局
        if (viewType == TYPE_HEAD) {
            return new HeadViewHolder(mHeadView);
        }
        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(mFooterView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }


    class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        RecyclerView rl;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            rl = (RecyclerView) itemView.findViewById(R.id.rl);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //position   ==   0  mHeadView    HeadViewHolder

        //判断当前是否是头布局，如果是头布局，那么直接return
        if (isHead(position)) {
            return;
        }

        if (isFooter(position)) {
            return;
        }

        //这里需要注意，取值应该是从position-1 开始，因为position ==0 已经被mHeadView占用了
        if (mHeadView != null) {
            position = position - 1;
        }
        ((ItemViewHolder) holder).tv_name.setText(homes.get(position).getName());


        //新专辑上架   3
        //最新音乐    2
        //推荐歌单    1

        int spanCount = 2;
        if(homes.get(position).getName().equals("新专辑上架")){
            spanCount = 3;
        }else if(homes.get(position).getName().equals("最新音乐")){
            spanCount = 2;
        }else if(homes.get(position).getName().equals("推荐歌单")){
            spanCount = 1;
        }

        MyAdapter myAdapter = new MyAdapter(homes.get(position).getPlayListBeen());
        ((ItemViewHolder) holder).rl.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
        ((ItemViewHolder) holder).rl.setAdapter(myAdapter);

    }

    class MyAdapter extends RecyclerView.Adapter{
        ArrayList<HomeResponse.ResultsBean.PlayListBean> playListBeen;

        public MyAdapter(ArrayList<HomeResponse.ResultsBean.PlayListBean> playListBeen) {
            this.playListBeen = playListBeen;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_child,parent,false);
            MyItemViewHolder holder = new MyItemViewHolder(view);

            return holder;
        }

        class MyItemViewHolder extends RecyclerView.ViewHolder{
            SmartImageView siv;
            TextView tv_album;
            TextView tv_name;
            public MyItemViewHolder(View itemView) {
                super(itemView);
                siv = (SmartImageView) itemView.findViewById(R.id.siv);
                tv_album = (TextView) itemView.findViewById(R.id.tv_album);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);

            }
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyItemViewHolder itemViewHolder = (MyItemViewHolder) holder;
            HomeResponse.ResultsBean.PlayListBean playListBean = playListBeen.get(position);
            String picUrl = playListBean.getPicUrl();
            itemViewHolder.siv.setImageUrl(picUrl);
        }

        @Override
        public int getItemCount() {
            return playListBeen.size();
        }
    }




    private boolean isHead(int position) {
        return mHeadView != null && position == 0;
    }

    private boolean isFooter(int position) {
        //getItemCount 获取item的个数
        return mFooterView != null && position == getItemCount() - 1;
    }

    @Override
    public int getItemCount() {
        return homes.size() + (mHeadView != null ? 1 : 0) + (mFooterView != null ? 1 : 0);
    }
}