package com.demo.automateliquibase.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScriptRequest implements Serializable {
    private static final long serialVersionUID = 5591916559716259364L;

    private String id;
    private String author;
    private String tableName;
    @JsonProperty("extras")
    private Map<String, String> extras;
    private MultipartFile file;
    private String operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "ScriptRequest{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", tableName='" + tableName + '\'' +
                ", extras=" + extras +
                ", file=" + file +
                ", operation='" + operation + '\'' +
                '}';
    }
}
