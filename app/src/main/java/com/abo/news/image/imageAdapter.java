package com.abo.news.image;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abo.news.Http.ImageLoaderUtils;
import com.abo.news.Http.ToolsUtil;
import com.abo.news.R;
import com.abo.news.beans.ImageBean;

import java.util.List;

/**
 * Created by abo on 16/8/17.
 */
public class imageAdapter extends RecyclerView.Adapter<imageAdapter.viewHolder> {

    private Context mContext;
    private List<ImageBean> mData;
    private OnItemClickListener mOnItemClickListener;
    private int mMaxWidth;
    private int mMaxHeight;

    public imageAdapter(Context context){
        this.mContext = context;
        mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 20;
        mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) -
                ToolsUtil.dip2px(mContext, 96);
    }

    public void setData(List<ImageBean> data){
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public imageAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image,parent,false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        ImageBean imageBean = mData.get(position);
        if (imageBean == null){
            return;
        }
        float scale = (float)imageBean.getWidth() / (float) mMaxWidth;
        int height = (int)(imageBean.getHeight() / scale);
        if(height > mMaxHeight) {
            height = mMaxHeight;
        }
        holder.mTextView.setText(imageBean.getTitle());
        holder.mImageView.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        ImageLoaderUtils.display(mContext,holder.mImageView,imageBean.getThumburl());
    }



    @Override
    public int getItemCount() {
        if (mData == null){
            return 0;
        }
        return mData.size();
    }
//三元表达式
    public ImageBean getItem(int position){
        return mData ==  null? null:mData.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public  interface OnItemClickListener{
        void onItemClick(View view, int position );
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public ImageView mImageView;
        public viewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.title_image);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getPosition());
            }
        }
    }
}
