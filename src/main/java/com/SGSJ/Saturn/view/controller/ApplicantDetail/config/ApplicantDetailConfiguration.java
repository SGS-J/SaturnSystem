package com.SGSJ.Saturn.view.controller.ApplicantDetail.config;

import com.SGSJ.Saturn.view.controller.ApplicantDetail.ApplicantDetailController;
import com.SGSJ.Saturn.view.controller.ApplicantMain.UserProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicantDetailConfiguration {
    public void configApplicantButtons(ApplicantDetailController controller) {
        UserProperty userData = controller.getUserData().getObject();
        String state = userData.getState();

        controller.getAcceptBtn().setVisible(false);
        controller.getRejectBtn().setVisible(false);

        switch (state) {
            case "ACEPTADO" -> controller.getDeleteBtn().setVisible(false);
            case "RECHAZADO" -> controller.getDeleteBtn().setVisible(true);
            case "EN_ESPERA" -> {
                controller.getAcceptBtn().setVisible(true);
                controller.getRejectBtn().setVisible(true);
                controller.getDeleteBtn().setVisible(true);
            }
        }
    }
}
