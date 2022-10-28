package com.mertkadir.loopcrypto.model;

import com.google.gson.annotations.SerializedName;



//https://api.nomics.com/v1/currencies/ticker?key=f7088d9112d20d3f4ac37ab72000c247055e985c

public class CryptoModel {



    @SerializedName("currency")
    public
    String currency;
    @SerializedName("price")
    public
    String price;


}
