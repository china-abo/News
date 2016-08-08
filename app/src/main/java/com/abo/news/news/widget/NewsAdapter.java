package com.abo.news.news.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abo.news.R;
import com.abo.news.beans.NewsBean;

import java.util.List;

/**
 * Created by abo on 16/8/5.
 * 新闻列表适配器
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final int TYPE_ITEN = 0;
    private static final int TYPE_FOOTER = 1;
    private OnItemClickListener mOnItemClickListener;
    private boolean mShowFooter = true;
    private List<NewsBean> mDate;

    //    判断item类型，是否为最后一个
    public int getItemType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEN;
        }
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEN) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            ViewHolder vh =new ViewHolder(view);
            return vh;
        }else{

        }
        return null;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return mDate.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public TextView mDesc;
        public ImageView mNewsImg;

        public ViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.new_text_title);
            mDesc = (TextView) v.findViewById(R.id.new_text_title);
            mNewsImg = (ImageView) v.findViewById(R.id.new_image);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }


    }


}
