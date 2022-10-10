package com.SGSJ.Saturn.view;

public enum SaturnView {
    REGISTER {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/register.fxml";
        }
    },
    LOG_IN {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/log_in.fxml";
        }
    },
    VACANCY_MAIN {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/vacancy_main.fxml";
        }
    },
    VACANCY_DETAIL {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/vacancy_detail.fxml";
        }
    },
    USER_MAIN {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/user_main.fxml";
        }
    },
    USER_DETAIL {
        @Override
        public String getSaturnViewPath() {
            return "./javafx/user_detail.fxml";
        }
    };
    public abstract String getSaturnViewPath();
}
