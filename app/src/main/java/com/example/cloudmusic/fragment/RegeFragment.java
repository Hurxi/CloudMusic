package com.example.cloudmusic.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cloudmusic.R;
import com.example.cloudmusic.bean.LoginResponse;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 若希 on 2017/6/6.
 */

public class RegeFragment extends BaseFragment {
    EditText etName,etPass;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_rege,null);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etName= (EditText) view.findViewById(R.id.et_name_rege);
        etPass= (EditText) view.findViewById(R.id.et_password_rege);
        AppCompatButton btnReg= (AppCompatButton) view.findViewById(R.id.btn_rege);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("请等待...");
                rege();
            }
        });
        ImageView ivBack= (ImageView) view.findViewById(R.id.back_rege);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void rege() {
        String name=etName.getText().toString();
        String password=etPass.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("username", name);
        params.put("password", password);
        JSONObject jsonObject = new JSONObject(params);



        OkGo.post("https://leancloud.cn:443/1.1/users")
                .tag(this)
                .upJson(jsonObject.toString())
                .headers("X-LC-Id", " kCFRDdr9tqej8FRLoqopkuXl-gzGzoHsz")
                .headers("X-LC-Key", "bmEeEjcgvKIq0FRaPl8jV2Um")
                .headers("Content-Type", "application/json")

                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        closeProgressDialog();
                        if (response.code()==202){
                            Toast.makeText(getActivity(), "注册失败，用户名重复", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        closeProgressDialog();
                        if (response.code()==201){
                            Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }
}
