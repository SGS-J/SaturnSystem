package com.SGSJ.Saturn.view;

import com.SGSJ.Saturn.SaturnSystemApplication;

public enum SaturnView {
    REGISTER {
        @Override
        public String getSaturnViewPath() {
            if(SaturnSystemApplication.isUserLogged()) {
                return "./javafx/applicant_main.fxml";
            }
            return "./javafx/register.fxml";
        }
    },
    LOG_IN {
        @Override
        public String getSaturnViewPath() {
            if(SaturnSystemApplication.isUserLogged()) {
                return "./javafx/applicant_main.fxml";
            }
            return "./javafx/log_in.fxml";
        }
    },
    VACANCY_MAIN {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/vacancy_main.fxml";
        }
    },
    VACANCY_DIALOG_CREATE {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/dialog_create_vacancy.fxml";
        }
    },
    VACANCY_DETAIL {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/vacancy_detail.fxml";
        }
    },
    APPLICANT_MAIN {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/applicant_main.fxml";
        }
    },
    APPLICANT_DETAIL {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/applicant_detail.fxml";
        }
    };
    public abstract String getSaturnViewPath();
}
