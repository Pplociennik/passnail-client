package com.passnail.gui.config;

import java.util.ResourceBundle;

public enum FxmlView {

    MAIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.app.title");
        }

        @Override
        public String getFxmlFile() {
            return "/main.fxml";
        }
    }, AUTH {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/auth.fxml";
        }
    }, NEWCREDENTIALS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("new.creds.title");
        }

        @Override
        public String getFxmlFile() {
            return "/newpass.fxml";
        }
    };

    public abstract String getTitle();
    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
