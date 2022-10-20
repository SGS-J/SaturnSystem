package com.SGSJ.Saturn.view.controller.ApplicantMain;

import com.SGSJ.Saturn.domain.User.User;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Wraps User properties to observable values for javafx TableViews
public class UserProperty {
    private LongProperty id;
    private StringProperty name;
    private StringProperty mainPhone;
    private StringProperty secondaryPhone;
    private StringProperty email;
    private StringProperty state;
    private StringProperty pathToCV;

    public UserProperty() {
    }

    public UserProperty(User user) {
        this.id = new SimpleLongProperty(user.getUserId());
        this.name = new SimpleStringProperty(user.getName());
        this.mainPhone = new SimpleStringProperty(user.getPhoneNumbers());
        this.secondaryPhone = new SimpleStringProperty(user.getPhoneNumbers());
        this.email = new SimpleStringProperty(user.getEmail());
        this.state = new SimpleStringProperty(user.getState());
        this.pathToCV = new SimpleStringProperty(user.getPathToCV());
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMainPhone() {
        return mainPhone.get();
    }

    public StringProperty mainPhoneProperty() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone.set(mainPhone);
    }

    public String getSecondaryPhone() {
        return secondaryPhone.get();
    }

    public StringProperty secondaryPhoneProperty() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone.set(secondaryPhone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getPathToCV() {
        return pathToCV.get();
    }

    public StringProperty pathToCVProperty() {
        return pathToCV;
    }

    public void setPathToCV(String pathToCV) {
        this.pathToCV.set(pathToCV);
    }
}
