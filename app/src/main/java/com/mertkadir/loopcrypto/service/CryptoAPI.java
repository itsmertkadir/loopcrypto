package com.mertkadir.loopcrypto.service;

import com.mertkadir.loopcrypto.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    @GET("ticker?key=f7088d9112d20d3f4ac37ab72000c247055e985c")
    Call<List<CryptoModel>> getData();

}
