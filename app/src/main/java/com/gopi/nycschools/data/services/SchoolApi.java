package com.gopi.nycschools.data.services;

import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.model.SchoolRating;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchoolApi {
    @GET("s3k6-pzi2.json")
    Call<List<School>> getSchools();

    @GET("f9bf-2cp4.json")
    Call<List<SchoolRating>> getSchoolRate();
}
