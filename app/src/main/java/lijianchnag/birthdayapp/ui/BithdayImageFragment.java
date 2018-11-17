package lijianchnag.birthdayapp.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lijianchnag.birthdayapp.R;

/**
 * Created by lijianchang on 2018/11/11:16:03.
 * Des :
 */

public class BithdayImageFragment extends AppCompatDialogFragment {

    private View view;
    private AlertDialog.Builder builder;
    private AlertDialog mAlertDialog;
    @BindView(R.id.dialog_iv_birthimg)
    ImageView mIvBirth;
    @BindView(R.id.dialog_birthimg_iv_close)
    ImageView mIvClose;
    @OnClick(R.id.dialog_birthimg_iv_close) void dismiss1(){
        dismiss();
    }
    Context mContext;
    int mDrawableId;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        view  = LayoutInflater.from(mContext).inflate(R.layout.dialog_birthimg,null,false);
        ButterKnife.bind(this,view);
        builder.setView(view);
        mAlertDialog = builder.create();

        return mAlertDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
//            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
//            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.getWindow().setLayout(width, height);
            Window mWindow = dialog.getWindow();
//            WindowManager.LayoutParams lp = mWindow.getAttributes();
//            lp.dimAmount =0f;
//            mWindow.setAttributes(lp);
            mIvBirth.setImageResource(mDrawableId);
        }
//        mIvBirth.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                Glide.with(mContext)
////                        .load(mDrawableId)
////                        .into(mIvBirth);
//            }
//        },100);

    }

    public void setDrawId(int i) {
        mDrawableId = i;
    }
}
