package org.xiangbalao.selectname.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xiangbalao.selectname.R;
import org.xiangbalao.selectname.model.FiveLayout;

import java.util.List;

/**
 * Created by longtaoge on 17/2/1.
 */

public class MyFiveLayoutAdapter extends RecyclerView.Adapter<MyFiveLayoutAdapter.MyViewHolder> {

    public void setmDatas(List<FiveLayout> mDatas) {
        this.mDatas = mDatas;
    }

    private List<FiveLayout> mDatas;
    private Context mContext;
    private LayoutInflater inflater;


    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private ItemClickListener mItemClickListener;


    public MyFiveLayoutAdapter(Context context, List<FiveLayout> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        FiveLayout mFiveLayout = mDatas.get(position);


        StringBuilder bihua = new StringBuilder();

        bihua.append(mFiveLayout.getFirstBihua())
                .append("画-")
                .append(mFiveLayout.getSecondBihua())
                .append("画-")
                .append(mFiveLayout.getThirdBihua())
                .append("画");
        holder.tv_bihua.setText(bihua.toString());


        StringBuilder wuge = new StringBuilder();
        wuge.append("总格-")
                .append(mFiveLayout.getZhongge())
                .append(" 天格-")
                .append(mFiveLayout.getTiange())
                .append(" 地格-")
                .append(mFiveLayout.getDige())
                .append(" 人格-")
                .append(mFiveLayout.getRenge())
                .append(" 外格-")
                .append(mFiveLayout.getWaige());

        holder.tv_fivelayout.setText(wuge.toString());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {

                    mItemClickListener.onClick(position);
                }
            }
        });
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_fivelayout, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public TextView tv_title;
        public TextView tv_bihua;
        public TextView tv_fivelayout;
        public LinearLayout root_view;

        public MyViewHolder(View rootView) {

            super(rootView);
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_bihua = (TextView) rootView.findViewById(R.id.tv_bihua);
            this.tv_fivelayout = (TextView) rootView.findViewById(R.id.tv_fivelayout);
            this.root_view = (LinearLayout) rootView.findViewById(R.id.root_view);
        }

    }


    public interface ItemClickListener {
        void onClick(int position);

    }


}
