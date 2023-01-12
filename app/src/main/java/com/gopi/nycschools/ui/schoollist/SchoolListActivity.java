package com.gopi.nycschools.ui.schoollist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.gopi.nycschools.R;
import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.remote.SchoolRemoteRepo;
import com.gopi.nycschools.data.services.SchoolService;
import com.gopi.nycschools.ui.base.BaseActivity;
import com.gopi.nycschools.ui.schooldetails.SchoolRatingsDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolListActivity extends BaseActivity<SchoolListPresenter>
        implements SchoolsView, SchoolListAdapter.SchoolListener {

    SchoolListAdapter schoolListAdapter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @NonNull
    @Override
    protected SchoolListPresenter createPresenter() {
        return new SchoolListPresenter(this,
                SchoolRemoteRepo.getInstance(SchoolService.getInstance().getSchoolApi()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_list_screen);
        ButterKnife.bind(this);
        schoolListAdapter = new SchoolListAdapter(this);
        recyclerView.setAdapter(schoolListAdapter);
        presenter.onAttach();
    }

    @Override
    public void showSchoolList(List<School> schoolList) {
        schoolListAdapter.setItems(schoolList);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSchoolSelected(School school) {
        Intent intent = new Intent(this, SchoolRatingsDetailsActivity.class);
        intent.putExtra("school",school);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        cancelProgressDialog();
    }
}
