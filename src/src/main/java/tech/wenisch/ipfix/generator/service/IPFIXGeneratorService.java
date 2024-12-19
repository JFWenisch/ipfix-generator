package tech.wenisch.ipfix.generator.service;


import org.springframework.stereotype.Service;

@Service
public class IPFIXGeneratorService {
    public String processInput(String inputValue) {
        // Perform some processing with the input value
        return "Processed value: " + inputValue;
    }
}
