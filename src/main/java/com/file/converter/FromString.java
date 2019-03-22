package com.file.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;

public class FromString {
    public static void main(String[] args) throws IOException {
        String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";

        // parse JSON
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
        // save it as YAML
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);

        System.out.println(jsonAsYaml);

        System.out.println();

        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter("src/main/resources/test.yaml");
        yaml.dump(jsonAsYaml, writer);

    }
}
