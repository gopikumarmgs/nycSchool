package com.gopi.nycschools.ui.schooldetails;

import com.gopi.nycschools.data.model.School;
import com.gopi.nycschools.data.model.SchoolRating;
import com.gopi.nycschools.data.remote.SchoolDataSource;
import com.gopi.nycschools.data.remote.SchoolRemoteRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SchoolRatingsDetailsPresenterTest {

    @Mock
    private SchoolRatingsDetailsView view;
    @Mock
    private SchoolRemoteRepo schoolRemoteRepo;
    @Captor
    private ArgumentCaptor<SchoolDataSource.LoadSchoolRateCallback> loadSchoolRateCallbackArgumentCaptor;
    private SchoolRatingsDetailsPresenter presenter;

    @Before
    public void setUp() {
        presenter = new SchoolRatingsDetailsPresenter(view, new School(), schoolRemoteRepo);
    }

    @Test
    public void showMovies_WhenSchoolRatingsListsCallSuccessAndMatchSchool() {
        presenter.onAttach();
        List<SchoolRating> schoolRatingsList = Arrays.asList(new SchoolRating(), new SchoolRating());
        Mockito.verify(schoolRemoteRepo).getSchoolRatings(loadSchoolRateCallbackArgumentCaptor.capture());
        loadSchoolRateCallbackArgumentCaptor.getValue().onSchoolsRatingServiceSuccess(schoolRatingsList);
    }

    @Test
    public void showNoMovies_WhenGetchoolRatingsListCallSuccessAndSchoolListIsEmpty() {
        presenter.onAttach();
        Mockito.verify(schoolRemoteRepo).getSchoolRatings(loadSchoolRateCallbackArgumentCaptor.capture());
        loadSchoolRateCallbackArgumentCaptor.getValue().onDataNotAvailable();
        Mockito.verify(view).showErrorMessage();
    }

    @Test
    public void showErrorMessage_WhenGetchoolRatingsCallFailed() {
        presenter.onAttach();
        Mockito.verify(schoolRemoteRepo).getSchoolRatings(loadSchoolRateCallbackArgumentCaptor.capture());
        loadSchoolRateCallbackArgumentCaptor.getValue().onError();
        Mockito.verify(view).showErrorMessage();
    }
}