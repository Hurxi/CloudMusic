package com.example.cloudmusic.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;

/**
 * 所有fragment的父类
 * Created by 若希 on 2017/6/7.
 */

public class BaseFragment extends Fragment {
    ProgressDialog dialog;

    public void showProgressDialog(String msg){
        if (dialog==null){
            dialog=new ProgressDialog(getActivity());
        }
        dialog.setMessage(msg);
        dialog.show();
    }
    public void closeProgressDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

}
