package com.gopi.nycschools.ui.schooldetails;

import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.model.SchoolRating;
import com.gopi.nycschools.data.remote.SchoolDataSource;
import com.gopi.nycschools.data.remote.SchoolRemoteRepo;
import com.gopi.nycschools.ui.base.BasePresenter;

import java.util.List;

public class SchoolRatingsDetailsPresenter extends BasePresenter<SchoolRatingsDetailsView> {

    private School school;
    private final SchoolRemoteRepo schoolRemoteRepo;
    boolean matchFound = false;

    public SchoolRatingsDetailsPresenter(SchoolRatingsDetailsView view, School school, SchoolRemoteRepo schoolRemoteRepo) {
        super(view);
        this.school = school;
        this.schoolRemoteRepo = schoolRemoteRepo;
    }

    public void onAttach() {
        getSchoolRating();
    }

    /**
     * Network Call to fetch the School List
     **/
    private void getSchoolRating() {
        view.showLoading();
        schoolRemoteRepo.getSchoolRatings(new SchoolDataSource.LoadSchoolRateCallback() {
            @Override
            public void onSchoolsRatingServiceSuccess(List<SchoolRating> schoolRatings) {
                view.hideLoading();
                //Call Success
                for(SchoolRating schoolRating : schoolRatings) {
                    //Match the Selected School
                    if(schoolRating.dbn.equalsIgnoreCase(school.dbn)) {
                        view.showSchoolRating(schoolRating, school);
                        matchFound = true;
                        break;
                    }
                } if(!matchFound) {
                    //Not Match the Selected School
                    view.showErrorMessage();
                }
            }

            @Override
            public void onDataNotAvailable() {
                view.hideLoading();
                view.showErrorMessage();
            }

            @Override
            public void onError() {
                view.hideLoading();
                view.showErrorMessage();
            }
        });
    }
}
