package com.gopi.nycschools.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseActivity<Presenter extends BasePresenter> extends FragmentActivity {

    protected Presenter presenter;
    protected ProgressDialog mDialog;

    @NonNull
    protected abstract Presenter createPresenter();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    protected void showProgressDialog() {
        mDialog = new ProgressDialog(BaseActivity.this);
        mDialog.setMessage("Loading..");
        mDialog.setIndeterminate(false);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    protected void cancelProgressDialog() {
        mDialog.cancel();
    }

}