package com.app.heydriver.heydriver.model;

public interface ObdProgressListener {

    void stateUpdate(final ObdCommandJob job);

}