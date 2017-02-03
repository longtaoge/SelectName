package org.xiangbalao.selectname.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xiangbalao.selectname.R;

import java.util.List;

/**
 * Created by longtaoge on 17/2/1.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;




    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private ItemClickListener mItemClickListener;


    public MyRecyclerAdapter(Context context, List<String> datas) {
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


        String name =mDatas.get(position);

        if (name.length()>=2){

            name="魏"+name;
        }


        holder.tv.setText(name);

        holder.rootview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mItemClickListener!=null){

                    mItemClickListener.onClick(position);
                }
            }
        });
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_name, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        View rootview;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_name);
            rootview = view.findViewById(R.id.root_view);

        }

    }


   public interface ItemClickListener{
       void onClick(int position);

    }


}
