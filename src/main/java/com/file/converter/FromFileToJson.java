package com.file.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("Duplicates")
public class FromFileToJson {
    public static void main(String[] args) throws IOException {

        // create source file or read existing one
        File sourceFile = new File("src/main/resources/swagger.json");
        if (sourceFile.createNewFile()) {
            System.out.println("source file created");
        } else {
            System.out.println("source file already exists");
        }

        // parse JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readValue(sourceFile, JsonNode.class);

        // create target file or read existing one
        File targetFile = new File("src/main/resources/file.json");
        targetFile.createNewFile();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(targetFile, json);
    }
}
