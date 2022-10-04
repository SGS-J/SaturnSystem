package com.SGSJ.Saturn.domain.Vacancy;

public class Vacancy {
    private Long vacancyId;
    private String name;
    private Integer jobOffer;

    public Long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(Integer jobOffer) {
        this.jobOffer = jobOffer;
    }
}
