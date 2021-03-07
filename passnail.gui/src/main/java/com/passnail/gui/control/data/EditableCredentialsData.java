package com.passnail.gui.control.data;

/**
 * Created by: Pszemko at niedziela, 07.03.2021 03:50
 * Project: passnail-client
 */
public enum EditableCredentialsData {

    ORIGINAL {
        private String description;

        private String shortName;

        private String login;

        private String password;

        private String url;


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }, EDITABLE {
        private String description;

        private String shortName;

        private String login;

        private String password;

        private String url;


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    };


    public abstract void setDescription(String description);

    public abstract String getShortName();

    public abstract void setShortName(String shortName);

    public abstract String getLogin();

    public abstract void setLogin(String login);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getUrl();

    public abstract String getDescription();

    public abstract void setUrl(String url);
}
