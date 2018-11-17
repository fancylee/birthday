package lijianchnag.birthdayapp.view.alphaviewpager.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lijianchnag.birthdayapp.R;
import lijianchnag.birthdayapp.bean.BirthDayBean;
import lijianchnag.birthdayapp.view.CircleImageView;
import lijianchnag.birthdayapp.view.listener.OnItemClickListener;


/**
 * Created by 13155 on 2017/1/11:11:10.
 * Des :
 */

public class BirthDayAdapter extends RecyclingPagerAdapter {

//    private final Typeface typeface1;
    private List<BirthDayBean> mList;

    OnItemClickListener onItemClickListener;

    private Context mContext;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BirthDayAdapter(Context mContext, List<BirthDayBean> mList){
        super();
        this.mContext = mContext;
        this.mList = mList;
//        typeface1 = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Bold.ttf");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        position = position % (mList.size());
        ViewHolder mHodler ;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_birthview, null);
            mHodler = new ViewHolder(convertView);
        }else{

            mHodler = (ViewHolder) convertView.getTag();
        }

        mHodler.mIv.setImageResource(mList.get(position).getmBigDrawableId());
//        mHodler.mTv.setTypeface(typeface1);
        mHodler.mTv.setText(mList.get(position).title);
//        Glide.with(mContext)
//                .load(mHodler.mIv);
        convertView.setTag(R.id.tag_first,position);
        final int finalPosition = position;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(finalPosition);
                }
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return mList.size() == 0?0:mList.size();
    }


    public class ViewHolder
    {


        View convertView;
        @BindView(R.id.item_birthview_iv)
        CircleImageView mIv;
        @BindView(R.id.item_birthview_tv)
        TextView mTv;

        public ViewHolder(View convertView) {
            //
            this.convertView = convertView;
            // 把当前的hodler设置到标签中
            ButterKnife.bind(this,convertView);
            convertView.setTag(this);
        }
    }
}
