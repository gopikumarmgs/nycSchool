package com.gopi.nycschools.data.remote;

import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.model.SchoolRating;

import java.util.List;

public interface SchoolDataSource {

    interface LoadSchoolsCallback {
        void onSchoolsListServiceSuccess(List<School> schoolList);
        void onDataNotAvailable();
        void onError();
    }

    interface LoadSchoolRateCallback {
        void onSchoolsRatingServiceSuccess(List<SchoolRating> schoolRatings);
        void onDataNotAvailable();
        void onError();
    }

    void getSchools(LoadSchoolsCallback callback);

    void getSchoolRatings(LoadSchoolRateCallback schoolRateCallback);
}
