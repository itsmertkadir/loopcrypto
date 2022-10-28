package com.mertkadir.loopcrypto.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mertkadir.loopcrypto.R;
import com.mertkadir.loopcrypto.adapter.RecyclerViewAdapter;
import com.mertkadir.loopcrypto.databinding.ActivityMainBinding;
import com.mertkadir.loopcrypto.model.CryptoModel;
import com.mertkadir.loopcrypto.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://api.nomics.com/v1/currencies/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);

        recyclerView = findViewById(R.id.recyclerView);

        //Json & Retrofit
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Timer timer = new Timer();

                    timer.scheduleAtFixedRate(new TimerTask() {
                                                  @Override
                                                  public void run() {
                                                      loadData();
                                                  }
                                              },
                            6000, 6000);
                }catch (Exception e){

                }
            }
        }).start();



    }

    private void loadData() {


        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);
        Call<List<CryptoModel>> call = cryptoAPI.getData();




        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {

                if (response.isSuccessful()) {
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void mainMenu(View view) {

    }
    public void setting(View view) {

    }
    public void profil(View view){

    }

}