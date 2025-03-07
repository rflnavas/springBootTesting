package com.rnr.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rnr.app.exceptions.MarshallingException;
import com.rnr.app.exceptions.NotFoundResource;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;

public final class ResourceHelper {

    private ResourceHelper() {

    }

    public static File getFileFromResource(String resourceName) {
        Objects.requireNonNull(resourceName);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = Optional.ofNullable(classLoader.getResource(resourceName))
                .orElseThrow(() -> new NotFoundResource(resourceName + " not found"));
        return new File(resource.getFile());
    }

    public static <T> T entityFromResource(String resourceName, Class<T> clazz) {
        File file = getFileFromResource(resourceName);
        try {
            return new ObjectMapper().readValue(file, clazz);
        } catch(Exception e) {
            throw new MarshallingException("Deserialization error.", e);
        }
    }

    @SneakyThrows
    public static String getContent(String resourceName) {
        File file = getFileFromResource(resourceName);
        return Files.readString(file.toPath());
    }

    public static <T> T entityFromContent(String content, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(content, clazz);
        } catch(Exception e) {
            throw new MarshallingException("Deserialization error.", e);
        }
    }
}
