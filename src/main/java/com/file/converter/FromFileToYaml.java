package com.file.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

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


        final String fileName = "src/main/resources/file.yaml";

        // parse JSON and save it as YAML string
        JsonNode jsonNodeTree = new ObjectMapper().readTree(sourceFile);
        String yamlString = new YAMLMapper().writeValueAsString(jsonNodeTree);

        // create YAML interface with configured dumper options
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        // get YAML objects
        InputStream fis = new FileInputStream(fileName);
        Map<String, Object> oldYaml = yaml.load(fis);
        Map<String, Object> newYaml = yaml.load(yamlString);

        // update file content
        if (!newYaml.equals(oldYaml)) {
            System.out.println("API documentation has been updated");
            FileWriter writer = new FileWriter(fileName);
            yaml.dump(newYaml, writer);
        }
    }
}
