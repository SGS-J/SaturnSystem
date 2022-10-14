package com.SGSJ.Saturn.domain.Vacancy;

import org.springframework.stereotype.Component;

@Component
public class Vacancy {
    private Long vacancyId;
    private String name;
    private Integer jobOffer;
    private String vacancyUUID;

    public String getVacancyUUID() {
        return vacancyUUID;
    }

    public void setVacancyUUID(String vacancyUUID) {
        this.vacancyUUID = vacancyUUID;
    }

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
