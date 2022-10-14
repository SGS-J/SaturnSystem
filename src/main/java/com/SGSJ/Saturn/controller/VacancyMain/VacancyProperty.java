package com.SGSJ.Saturn.controller.VacancyMain;

import com.SGSJ.Saturn.domain.Vacancy.Vacancy;
import javafx.beans.property.*;

public class VacancyProperty {
    private LongProperty id;
    private StringProperty name;
    private IntegerProperty jobOffer;

    public VacancyProperty() {
    }

    public VacancyProperty(Vacancy vacancy) {
        this.id = new SimpleLongProperty(vacancy.getVacancyId());
        this.name = new SimpleStringProperty(vacancy.getName());
        this.jobOffer = new SimpleIntegerProperty(vacancy.getJobOffer());
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

    public int getJobOffer() {
        return jobOffer.get();
    }

    public IntegerProperty jobOfferProperty() {
        return jobOffer;
    }

    public void setJobOffer(int jobOffer) {
        this.jobOffer.set(jobOffer);
    }
}
