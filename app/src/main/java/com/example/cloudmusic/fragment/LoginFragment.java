package com.example.cloudmusic.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cloudmusic.MainActivity;
import com.example.cloudmusic.R;
import com.example.cloudmusic.bean.LoginResponse;
import com.example.cloudmusic.utils.AppStringUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 若希 on 2017/6/5.
 */

public class LoginFragment extends BaseFragment {
    Unbinder unbinder;
    private EditText etName;
    private EditText etPass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatButton btnLogin= (AppCompatButton) view.findViewById(R.id.btn_login);
        AppCompatButton btnRege= (AppCompatButton) view.findViewById(R.id.btn_register);
        etName = (EditText) view.findViewById(R.id.et_name);
        etPass = (EditText) view.findViewById(R.id.et_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("请等待...");
                login();
            }
        });
        btnRege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegeFragment regeFragment=new RegeFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_content,regeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void login() {
        String name=etName.getText().toString();
        String password=etPass.getText().toString();
        if (!AppStringUtil.checkUserName(name)){
            etName.setError("用户名格式不正确");
            return;
        }
        if (!AppStringUtil.checkPassword(password)){
            etPass.setError("密码格式不正确");
            return;
        }
        OkGo.get("https://leancloud.cn:443/1.1/login")
                .tag(this)
                .headers("X-LC-Id", "kCFRDdr9tqej8FRLoqopkuXl-gzGzoHsz")
                .headers("X-LC-Key", "bmEeEjcgvKIq0FRaPl8jV2Um")
                .headers("Content-Type", "application/json")
                .params("username",name)
                .params("password",password)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                            Toast.makeText(getActivity(), "登陆失败", Toast.LENGTH_SHORT).show();
                        closeProgressDialog();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if(response.code() == 200) {
                            Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            Gson gson = new Gson();
                            LoginResponse res = gson.fromJson(s, LoginResponse.class);
                        }
                        else{
                            Toast.makeText(getActivity(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                        closeProgressDialog();

                    }

                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



}
