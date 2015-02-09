package com.arcbees.client.widgets.schools;

import java.util.List;

import javax.inject.Inject;

import com.arcbees.client.School;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ComputerView extends ViewWithUiHandlers<SchoolsPresenterUiHandlers>
        implements SchoolsPresenter.MyView {
    interface Binder extends UiBinder<Widget, ComputerView> {
    }

    @UiField
    SpanElement computerName;

    @Inject
    ComputerView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void showAllSchools(List<School> schools) {
        // Updating the view with all schools fetched
    }

    @Override
    public void showSchoolDetails(School school) {
        // Updating the view with a specific school details
    }

    @UiHandler("schoolSelectButton")
    public void onSchoolSelected(ClickEvent event) {
        // Let's imagine a button that selects a specific school from the UI
        int schoolId = 1; // Get the id from some UI Element

        getUiHandlers().onSchoolSelected(schoolId);
    }
}
