package com.gopi.nycschools.ui.schooldetails;

import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.model.SchoolRating;
import com.gopi.nycschools.ui.base.BaseView;

public interface SchoolRatingsDetailsView extends BaseView {

    void showSchoolRating(SchoolRating schoolRating, School school);

    void showErrorMessage();
}
