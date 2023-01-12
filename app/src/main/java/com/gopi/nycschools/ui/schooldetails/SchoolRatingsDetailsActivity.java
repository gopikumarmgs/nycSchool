package com.gopi.nycschools.ui.schooldetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.gopi.nycschools.R;
import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.model.SchoolRating;
import com.gopi.nycschools.data.remote.SchoolRemoteRepo;
import com.gopi.nycschools.data.services.SchoolService;
import com.gopi.nycschools.ui.base.BaseActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolRatingsDetailsActivity extends BaseActivity<SchoolRatingsDetailsPresenter> implements SchoolRatingsDetailsView {

    @NonNull
    @Override
    protected SchoolRatingsDetailsPresenter createPresenter() {
        return new SchoolRatingsDetailsPresenter(this,
                (School) getIntent().getExtras().getParcelable("school"),
                SchoolRemoteRepo.getInstance(SchoolService.getInstance().getSchoolApi()));
    }

    @BindView(R.id.school_address) TextView school_address;
    @BindView(R.id.school_overview) TextView school_overview;
    @BindView(R.id.school_email) TextView school_email;
    @BindView(R.id.school_sports) TextView school_sports;
    @BindView(R.id.progressBar1) ProgressBar progressBar1;
    @BindView(R.id.progressBar2) ProgressBar progressBar2;
    @BindView(R.id.progressBar3) ProgressBar progressBar3;
    @BindView(R.id.txtProgress1) TextView txtProgress1;
    @BindView(R.id.txtProgress2) TextView txtProgress2;
    @BindView(R.id.txtProgress3) TextView txtProgress3;
    @BindView(R.id.sat_test_no) TextView sat_test_no;
    @BindView(R.id.header_title) TextView headerTitle;
    @BindView(R.id.school_details_data) RelativeLayout school_details_data;
    @BindView(R.id.error_text) TextView errorText;
    @BindView(R.id.back_arrow) ImageView backArrow;
    @BindView(R.id.btn_navigate) Button navigateButton;
    @BindView(R.id.btn_call) Button callButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_details_screen);
        ButterKnife.bind(this);

        presenter.onAttach();
        bindEvents();
    }

    @Override
    public void showSchoolRating(SchoolRating schoolRating, final School school) {
        errorText.setVisibility(View.GONE);
        school_details_data.setVisibility(View.VISIBLE);

        //Load Texts
        school_address.setText(school.getPrimary_address_line_1());
        school_email.setText(school.getSchool_email());
        school_overview.setText(school.getOverview_paragraph());
        school_sports.setText(school.getSchool_sports()+school.getExtracurricular_activities());
        //Set Header
        headerTitle.setText(school.getSchool_name());

        //Load ProgressBar Details
        progressBar1.setProgress(Integer.parseInt(schoolRating.getSat_critical_reading_avg_score()));
        progressBar2.setProgress(Integer.parseInt(schoolRating.getSat_writing_avg_score()));
        progressBar3.setProgress(Integer.parseInt(schoolRating.getSat_math_avg_score()));

        txtProgress1.setText("SAT READING AVG \n" +schoolRating.getSat_critical_reading_avg_score());
        txtProgress2.setText("SAT WRITING AVG \n" +schoolRating.getSat_writing_avg_score());
        txtProgress3.setText("MATH AVG \n" +schoolRating.getSat_math_avg_score());

        sat_test_no.setText("No.Of SAT TEST TAKERS - " +schoolRating.getNum_of_sat_test_takers());

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+school.getPhone_number()));
                startActivity(intent);
            }
        });

        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", school.getLatitude(), school.getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

    }

    private void bindEvents() {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showErrorMessage() {
        errorText.setVisibility(View.VISIBLE);
        school_details_data.setVisibility(View.GONE);
        headerTitle.setText("");
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
