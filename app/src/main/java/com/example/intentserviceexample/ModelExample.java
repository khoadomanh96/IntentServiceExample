package com.example.intentserviceexample;

public class ModelExample {
    private boolean isRunning;
    private boolean isDone;
    private String url;

    public ModelExample(String url) {
        this.url = url;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
