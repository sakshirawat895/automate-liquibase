package com.demo.automateliquibase.service;

import com.demo.automateliquibase.model.ScriptRequest;
import com.demo.automateliquibase.xslsgenerator.ScriptGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateScriptService {

    private final ScriptGenerator scriptGenerator;

    @Autowired
    public GenerateScriptService(ScriptGenerator scriptGenerator) {
        this.scriptGenerator = scriptGenerator;
    }

    public byte[] generateInsertScript(ScriptRequest scriptRequest){
        return scriptGenerator.generateXML( scriptRequest );
    }
}
