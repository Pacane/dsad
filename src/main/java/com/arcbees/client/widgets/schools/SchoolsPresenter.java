package com.arcbees.client.widgets.schools;

import java.util.List;

import javax.inject.Inject;

import com.arcbees.client.School;
import com.arcbees.client.SchoolsService;
import com.arcbees.client.events.CommunicationErrorEvent;
import com.arcbees.client.widgets.schools.SchoolsPresenter.MyView;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class SchoolsPresenter extends PresenterWidget<MyView> implements SchoolsPresenterUiHandlers {
    interface MyView extends View {
        void showAllSchools(List<School> schools);

        void showSchoolDetails(School school);
    }

    private final ResourceDelegate<SchoolsService> schoolsServiceResourceDelegate;

    @Inject
    SchoolsPresenter(
            EventBus eventBus,
            MyView view,
            ResourceDelegate<SchoolsService> schoolsServiceResourceDelegate) {
        super(eventBus, view);

        this.schoolsServiceResourceDelegate = schoolsServiceResourceDelegate;
    }

    @Override
    protected void onReveal() {
        schoolsServiceResourceDelegate.withCallback(new AsyncCallback<List<School>>() {
            @Override
            public void onFailure(Throwable caught) {
                // This can be handled somewhere else, like a presenter that adds a toast message or
                // something similar to that mechanism
                CommunicationErrorEvent.fire(SchoolsPresenter.this);
                // You may want to create a custom type of AsyncCallback that overrides this,
                // so you don't have to test it everywhere.
            }

            @Override
            public void onSuccess(List<School> result) {
                getView().showAllSchools(result);
            }
        }).getSchools();
    }

    @Override
    public void onSchoolSelected(int id) {
        schoolsServiceResourceDelegate.withCallback(new AsyncCallback<School>() {
            @Override
            public void onFailure(Throwable caught) {
                CommunicationErrorEvent.fire(SchoolsPresenter.this);
            }

            @Override
            public void onSuccess(School result) {
                getView().showSchoolDetails(result);
            }
        }).getSchool(id);
    }
}
