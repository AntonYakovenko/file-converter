package com.file.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("Duplicates")
public class FromFileToYaml {
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
        JsonNode jsonNodeTree = objectMapper.readTree(sourceFile);

        // save it as YAML
        YAMLMapper yamlMapper = new YAMLMapper();
        String yamlString = yamlMapper.writeValueAsString(jsonNodeTree);

        // create YAML object
        Yaml yaml = new Yaml();
        Object yamlObject = yaml.load(yamlString);

        // write into target file
        FileWriter writer = new FileWriter("src/main/resources/file.yaml");
        yaml.dump(yamlObject, writer);
    }
}
