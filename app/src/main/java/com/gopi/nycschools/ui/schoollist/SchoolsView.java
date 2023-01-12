package com.gopi.nycschools.ui.schoollist;

import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.ui.base.BaseView;

import java.util.List;

interface SchoolsView extends BaseView {

    void showSchoolList(List<School> schoolList);

    void showErrorMessage();

}
