package com.gopi.nycschools.ui.schoollist;

import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.remote.SchoolDataSource;
import com.gopi.nycschools.data.remote.SchoolRemoteRepo;
import com.gopi.nycschools.ui.base.BasePresenter;

import java.util.List;

public class SchoolListPresenter extends BasePresenter<SchoolsView> {

    private final SchoolRemoteRepo schoolRemoteRepo;

    SchoolListPresenter(SchoolsView view, SchoolRemoteRepo schoolRemoteRepo) {
        super(view);
        this.schoolRemoteRepo = schoolRemoteRepo;
    }

    public void onAttach() {
        getSchoolList();
    }

    /**
     * Network Call to fetch the School List
     **/
    private void getSchoolList() {
        view.showLoading();
        schoolRemoteRepo.getSchools(new SchoolDataSource.LoadSchoolsCallback() {
            @Override
            public void onSchoolsListServiceSuccess(List<School> schoolList) {
                //Call Success
                view.hideLoading();
                view.showSchoolList(schoolList);
            }

            @Override
            public void onDataNotAvailable() {
                //Call Failure
                view.hideLoading();
                view.showErrorMessage();
            }

            @Override
            public void onError() {
                //Call Failure
                view.hideLoading();
                view.showErrorMessage();
            }
        });
    }

}
