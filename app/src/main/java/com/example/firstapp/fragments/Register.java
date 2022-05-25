package com.example.firstapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstapp.Home;
import com.example.firstapp.MainActivity;
import com.example.firstapp.R;
import com.example.firstapp.User;
import com.example.firstapp.UserAPI;
import com.example.firstapp.UserRegister;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register extends Fragment {
    public static String TAG = "TAG";

    EditText password,fullname,email,scannedarduino;
    Button sign;
    UserRegister userregister;

    public Register() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_register, container, false);


        password = view.findViewById(R.id.pass);
        sign = view.findViewById(R.id.regbtn);
        fullname = view.findViewById(R.id.fullname);
        email = view.findViewById(R.id.email);
        scannedarduino = view.findViewById(R.id.scannedarduino);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userregister = new UserRegister();
                userregister.setPassword(password.getText().toString());
                userregister.setEmail(email.getText().toString());
                userregister.setScannedarduino(scannedarduino.getText().toString());
                userregister.setFullname(fullname.getText().toString());

      Log.d(TAG, "onClick:  username" + password+" email" + email+" scannedarduino" + scannedarduino+" fullname" + fullname);
                SendPostReq(userregister);
            }
        });
        return  view;
    }

    private void SendPostReq(final UserRegister m) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<Void> call = userAPI.signUp(m);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(getContext(), "You have signed up successully & saved your data!!!!", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 409) {
                    Toast.makeText(getContext(), "email duplicated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to sign up!!!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get Response!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


