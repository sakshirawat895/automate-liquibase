package com.demo.automateliquibase.xslsgenerator;

import com.demo.automateliquibase.model.ScriptRequest;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.demo.automateliquibase.util.GenerateScriptConstants.INSERT;
import static com.demo.automateliquibase.util.GenerateScriptConstants.UPDATE;
import static com.demo.automateliquibase.util.GenerateScriptUtil.*;

@Component
public class ScriptGenerator {

    public byte[] generateXML(ScriptRequest scriptRequest){
        MultipartFile inputFile= scriptRequest.getFile();

        FileWriter fostream;
        PrintWriter out=null;
        InputStream inputStream=null;
        String strFilePrefix = "automate-liquibase-create";
        try{
            inputStream= inputFile.getInputStream();
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheet("Sheet1");

            fostream = new FileWriter(strFilePrefix+ ".xml");
            generateXMLContent(fostream, scriptRequest, sheet);

            inputStream= new FileInputStream( new File( "automate-liquibase-create.xml" ) );
            return IOUtils.toByteArray(inputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void generateXMLContent(FileWriter fostream, ScriptRequest scriptRequest, Sheet sheet){
        PrintWriter out = new PrintWriter(new BufferedWriter(fostream));
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.println("<changeSet author=\""+scriptRequest.getAuthor()+"\" id=\""+scriptRequest.getId()+"\">");

        boolean firstRow = true;
        int columnSize= 0;
        Map<Integer, String> map = new HashMap<>(); //Create map
        for (Row row : sheet) {
            if (firstRow == true) {
                columnSize= row.getLastCellNum();
                for(int i=0; i< columnSize; i++){
                    map.put(i, row.getCell( i ).getStringCellValue() );
                }
                firstRow = false;
                continue;
            }
            generateChildTags(out, columnSize, map, scriptRequest.getTableName(), scriptRequest.getOperation(), row);
        }
        out.write("</changeSet>");
        out.flush();
        out.close();
    }

    private void generateChildTags(PrintWriter out, int columnSize, Map<Integer, String> map, String tableName, String operation, Row row){
        if(operation.equalsIgnoreCase( INSERT ))
            generateInsertChildTag(out, columnSize, map, tableName, row);
        else if(operation.equalsIgnoreCase( UPDATE ))
            generateUpdateChildTag(out, columnSize, map, tableName, row);
    }
}
