package com.gopi.nycschools.ui.base;

public abstract class BasePresenter<View extends BaseView> {

    protected View view;

    protected BasePresenter(View view) {
        this.view = view;
    }

    void onDetach() {
        //avoid memory leak
        view = null;
    }
}