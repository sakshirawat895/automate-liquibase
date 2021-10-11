package com.demo.automateliquibase.web;

import com.demo.automateliquibase.model.ScriptRequest;
import com.demo.automateliquibase.service.GenerateScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScriptGeneratorController {

    private final GenerateScriptService generateScriptService;

    @Autowired
    public ScriptGeneratorController(GenerateScriptService generateScriptService) {
        this.generateScriptService = generateScriptService;
    }

    @PostMapping(value= "/generateScript", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<byte[]> insertScript(@ModelAttribute ScriptRequest scriptRequest){

        byte[] insertScript= generateScriptService.generateInsertScript( scriptRequest );
        HttpHeaders header = new HttpHeaders();

        header.setContentType( MediaType.valueOf( MediaType.APPLICATION_OCTET_STREAM_VALUE ) );
        header.set("Content-Disposition", "attachment; filename= automate-liquibase-create.xml");
        return new ResponseEntity<>(insertScript,header, HttpStatus.CREATED);
    }


}

