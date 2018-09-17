package com.example.wk.sigah.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.UserAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kharisma on 30/03/2018.
 */

public class FragmentChangePassword extends Fragment {
    View view;
    String tempIdPel,URL;
    String oldPassword,newPassword,confirmPassword;
    private ProgressDialog progress;
    private GetURL getURL=new GetURL(URL);

    private TextView txtNew,txtOld,txtConfirm;
    private Button btnChange;

    public FragmentChangePassword(String tempIdPel) {
        this.tempIdPel = tempIdPel;
    }

    public FragmentChangePassword() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.change_password,container,false);

        //Object layout getter
        txtOld=(TextView)view.findViewById(R.id.txtOldPassword);
        txtNew=(TextView)view.findViewById(R.id.txtNewPass);
        txtConfirm=(TextView)view.findViewById(R.id.txtConfirmPass);
        btnChange=(Button) view.findViewById(R.id.btnChange);

        OnClickButtonListener(getView());

        return view;
    }

    public void OnClickButtonListener(View view){
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPassword=txtOld.getText().toString();
                newPassword=txtNew.getText().toString();
                confirmPassword=txtConfirm.getText().toString();

                if(!confirmPassword.equals(newPassword)){
                    Toast.makeText(getActivity(),"Confirm new password not correct",Toast.LENGTH_SHORT).show();
                }else{
                    updatePassword();
                }
            }
        });
    }

    public void updatePassword(){
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAPI api = retrofit.create(UserAPI.class);
        Call<Value> call = api.updatePassword(tempIdPel,newPassword,oldPassword);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                progress.dismiss();
                Integer value=response.body().getValue();
                String message=response.body().getMessage();
                if(value==1)
                {
                    Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                    txtNew.setText("");
                    txtConfirm.setText("");
                    txtOld.setText("");
                }
                else{
                    Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getActivity(),"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
