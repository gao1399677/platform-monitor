package com.monitor.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) throws IOException {
        File sourceDirectory = new File(sourceDirectoryLocation);
        File destinationDirectory = new File(destinationDirectoryLocation);
        FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
    }

    public static void moveDirectoryToDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) throws IOException {
        File sourceDirectory = new File(sourceDirectoryLocation);
        File destinationDirectory = new File(destinationDirectoryLocation);
        FileUtils.moveDirectoryToDirectory(sourceDirectory, destinationDirectory, true);
    }

    public static File getFileByPath(String path) {
        File file = new File(path);
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
        }
        return file;
    }


}
