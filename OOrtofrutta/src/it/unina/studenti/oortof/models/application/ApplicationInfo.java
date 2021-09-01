package it.unina.studenti.oortof.models.application;

import it.unina.studenti.oortof.models.entities.ObservedModel;

public class ApplicationInfo extends ObservedModel {

    public static final int LEVEL_ERROR = 0;
    public static final int LEVEL_LOG = 1;


    private static final ApplicationInfo instance = new ApplicationInfo();


    private ApplicationMessage message;
    private ApplicationInfo() {

    }

    public static ApplicationInfo getInstance() {
        return instance;
    }

    public void setMessage(String newMessage, int level) {
        ApplicationMessage oldMessage = message;
        message = new ApplicationMessage(newMessage, level);
        firePropertyChange("message", oldMessage, message);
    }


    public ApplicationMessage getMessage() {
        return message;
    }


    @Override
    public void copyTo(ObservedModel other) {

    }
}

