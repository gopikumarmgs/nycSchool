package com.gopi.nycschools.data.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchoolService {

    private static final String URL = "https://data.cityofnewyork.us/resource/";
    private SchoolApi mSchoolApi;
    private static SchoolService singleton;

    private SchoolService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mSchoolApi = mRetrofit.create(SchoolApi.class);
    }

    public static SchoolService getInstance() {
        if (singleton == null) {
            singleton = new SchoolService();
        }
        return singleton;
    }

    public SchoolApi getSchoolApi() {
        return mSchoolApi;
    }
}
