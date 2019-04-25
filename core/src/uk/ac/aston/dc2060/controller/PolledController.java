package uk.ac.aston.dc2060.controller;

public interface PolledController {
    void update();

    int getUpdateFrequencyMs();
}
