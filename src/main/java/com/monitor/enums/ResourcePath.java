package com.monitor.enums;

public enum ResourcePath {
    SCENE_XML("krpano/scene.xml"),
    KRPANO_XML("krpano/krpano.xml"),;

    private String path;

    ResourcePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
