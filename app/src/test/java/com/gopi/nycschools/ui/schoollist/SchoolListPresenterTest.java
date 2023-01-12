package com.gopi.nycschools.ui.schoollist;

import com.gopi.nycschools.data.model.School;
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
public class SchoolListPresenterTest {

    @Mock
    private SchoolsView view;
    @Mock
    private SchoolRemoteRepo schoolRemoteRepo;
    @Captor
    private ArgumentCaptor<SchoolDataSource.LoadSchoolsCallback> loadSchoolsCallbackArgumentCaptor;
    private SchoolListPresenter presenter;

    @Before
    public void setUp() {
        presenter = new SchoolListPresenter(view, schoolRemoteRepo);
    }

    @Test
    public void showMovies_WhenSchoolListsCallSuccess() {
        presenter.onAttach();
        List<School> schoolList = Arrays.asList(new School(), new School());
        Mockito.verify(schoolRemoteRepo).getSchools(loadSchoolsCallbackArgumentCaptor.capture());
        loadSchoolsCallbackArgumentCaptor.getValue().onSchoolsListServiceSuccess(schoolList);
        Mockito.verify(view).showSchoolList((List<School>) Mockito.any());
    }

    @Test
    public void showNoMovies_WhenGetSchoolListCallSuccessAndSchoolListIsEmpty() {
        presenter.onAttach();
        Mockito.verify(schoolRemoteRepo).getSchools(loadSchoolsCallbackArgumentCaptor.capture());
        loadSchoolsCallbackArgumentCaptor.getValue().onDataNotAvailable();
        Mockito.verify(view).showErrorMessage();
    }

    @Test
    public void showErrorMessage_WhenGetSchoolListCallFailed() {
        presenter.onAttach();
        Mockito.verify(schoolRemoteRepo).getSchools(loadSchoolsCallbackArgumentCaptor.capture());
        loadSchoolsCallbackArgumentCaptor.getValue().onError();
        Mockito.verify(view).showErrorMessage();
    }
}