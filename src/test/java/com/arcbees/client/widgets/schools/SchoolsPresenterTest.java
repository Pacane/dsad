package com.arcbees.client.widgets.schools;

import java.util.List;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.School;
import com.arcbees.client.SchoolsService;
import com.arcbees.client.events.CommunicationErrorEvent;
import com.google.common.collect.Lists;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;

@RunWith(JukitoRunner.class)
public class SchoolsPresenterTest {
    @Inject
    SchoolsPresenter presenter;
    @Inject
    SchoolsPresenter.MyView view;

    @Inject
    ResourceDelegate<SchoolsService> schoolsServiceResourceDelegate;

    @Before
    public void setup() {
        givenDelegate(schoolsServiceResourceDelegate).useResource(SchoolsService.class);
    }

    @Test
    public void onSchoolSelected_shouldShowSchoolDetailsInView() {
        // given
        final int schoolId = 490482390;

        School someSchool = new School();
        someSchool.id = schoolId;

        givenDelegate(schoolsServiceResourceDelegate)
                .succeed()
                .withResult(someSchool)
                .when()
                .getSchool(schoolId);

        // when
        presenter.onSchoolSelected(schoolId);

        // then
        verify(view).showSchoolDetails(same(someSchool));
    }

    @Test
    public void onReveal_shouldFetchAllSchools() {
        // given
        List<School> schools = Lists.newArrayList();

        givenDelegate(schoolsServiceResourceDelegate)
                .succeed()
                .withResult(schools)
                .when()
                .getSchools();

        // when
        presenter.onReveal();

        // then
        verify(view).showAllSchools(same(schools));
    }

    @Test
    public void onReveal_shouldRaiseCommunicationErrorEvent_whenFetchingFails(EventBus eventBus) {
        // given
        givenDelegate(schoolsServiceResourceDelegate)
                .fail()
                .when()
                .getSchools();

        // when
        presenter.onReveal();

        // then
        verify(eventBus).fireEventFromSource(isA(CommunicationErrorEvent.class), same(presenter));
    }
}
