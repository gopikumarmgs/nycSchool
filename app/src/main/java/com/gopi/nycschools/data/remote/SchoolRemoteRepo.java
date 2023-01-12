package com.gopi.nycschools.data.remote;

import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.model.SchoolRating;
import com.gopi.nycschools.data.services.SchoolApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolRemoteRepo implements SchoolDataSource {

    private static SchoolRemoteRepo instance;
    private final SchoolApi schoolApi;
    private SchoolRemoteRepo(SchoolApi schoolApi) {
        this.schoolApi = schoolApi;
    }

    public static SchoolRemoteRepo getInstance(SchoolApi schoolApi) {
        if (instance == null) {
            instance = new SchoolRemoteRepo(schoolApi);
        }
        return instance;
    }

    @Override
    public void getSchools(final LoadSchoolsCallback callback) {
        schoolApi.getSchools().enqueue(new Callback<List<School>>() {

            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                List<School> schoolList = response.body();
                if (schoolList != null && !schoolList.isEmpty()) {
                    callback.onSchoolsListServiceSuccess(schoolList);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {
                callback.onError();
            }

        });
    }

    @Override
    public void getSchoolRatings(final LoadSchoolRateCallback schoolRateCallback) {
        schoolApi.getSchoolRate().enqueue(new Callback<List<SchoolRating>>() {

            @Override
            public void onResponse(Call<List<SchoolRating>> call, Response<List<SchoolRating>> response) {
                List<SchoolRating> schoolList = response.body();
                if (schoolList != null && !schoolList.isEmpty()) {
                    schoolRateCallback.onSchoolsRatingServiceSuccess(schoolList);
                } else {
                    schoolRateCallback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<List<SchoolRating>> call, Throwable t) {
                schoolRateCallback.onError();
            }
        });
    }
}
