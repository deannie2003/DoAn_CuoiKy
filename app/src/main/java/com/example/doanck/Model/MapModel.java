package com.example.doanck.Model;

import java.util.List;

public class MapModel {
    private Options options;

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}

class Options {
    private DefaultObject defaultObject;

    public DefaultObject getDefaultObject() {
        return defaultObject;
    }

    public void setDefaultObject(DefaultObject defaultObject) {
        this.defaultObject = defaultObject;
    }
}

class DefaultObject {
    private List<Double> center;
    private List<Double> bounds;
    private double zoom;
    private double minZoom;
    private double maxZoom;

    public List<Double> getCenter() {
        return center;
    }

    public void setCenter(List<Double> center) {
        this.center = center;
    }

    public List<Double> getBounds() {
        return bounds;
    }

    public void setBounds(List<Double> bounds) {
        this.bounds = bounds;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public double getMinZoom() {
        return minZoom;
    }

    public void setMinZoom(double minZoom) {
        this.minZoom = minZoom;
    }

    public double getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(double maxZoom) {
        this.maxZoom = maxZoom;
    }
}
