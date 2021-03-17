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
    }, LIBRARY {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("creds.lib.title");
        }

        @Override
        public String getFxmlFile() {
            return "/lib.fxml";
        }
    }, OPENEDCREDENTIALS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("creds.opened.title");
        }

        @Override
        public String getFxmlFile() {
            return "/openedPass.fxml";
        }
    }, GENERATORSETTINGS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("generator.settings.title");
        }

        @Override
        public String getFxmlFile() {
            return "/generatorSettings.fxml";
        }
    }, EDITABLECREDENTIALS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("creds.editable.title");
        }

        @Override
        public String getFxmlFile() {
            return "/editablePass.fxml";
        }
    }, SETTINGS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("app.settings.title");
        }

        @Override
        public String getFxmlFile() {
            return "/settings.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
