package com.rasto.accommodationbookingsystem.web.ui.utils;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.upload.MultiFileReceiver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhotoReceiver implements MultiFileReceiver {
    private List<File> files;

    public PhotoReceiver() {
        files = new ArrayList<>();
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos;
        try {
            File file = new File("/tmp/" + filename);
            file.createNewFile();
            files.add(file);
            fos = new FileOutputStream(file);
        } catch (IOException e) {
            Notification.show("Could not open file", 3000, Notification.Position.MIDDLE);
            return null;
        }

        return fos;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<String> getFilesPaths() {
        return files.stream().map(File::getAbsolutePath).collect(Collectors.toList());
    }
}
