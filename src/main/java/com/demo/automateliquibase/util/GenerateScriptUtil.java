package com.demo.automateliquibase.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.io.PrintWriter;
import java.util.Map;

import static org.apache.poi.ss.usermodel.CellType.BLANK;

public final class GenerateScriptUtil {

    private GenerateScriptUtil(){
        throw new UnsupportedOperationException("Utility Class");
    }


    public static String formatCell(Cell cell)
    {
        if (cell == null) {
            return "";
        }
        switch(cell.getCellType()) {
            case BLANK:
                return "";
            case STRING:
                return  cell.getStringCellValue();
            default:
                DataFormatter formatter = new DataFormatter();
                //String strValue = formatter.formatCellValue(cell);
                return formatter.formatCellValue(cell);
        }
    }

    public static boolean checkIfRowIsEmpty(Row row){
        for(int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++){
            Cell cell= row.getCell( cellNum );
            if(cell!= null && cell.getCellType()!=BLANK && StringUtils.isNotBlank( cell.toString() )){
                return false;
            }
        }
        return true;
    }


    public static void generateInsertChildTag(PrintWriter out, int columnSize, Map<Integer, String> map, String tableName, Row row) {
        out.println( "\t<insert tableName=\""+tableName+"\">" );
        for (int i = 0; i < columnSize; i++) {
            out.println( "\t\t<column name=\"" + map.get( i ) + "\" value=\"" + formatCell( row.getCell( i ) ) + "\"/>" );
        }
        out.println( "\t</insert>" );
    }

    public static void generateUpdateChildTag(PrintWriter out, int columnSize, Map<Integer, String> map, String tableName, Row row) {
        out.println( "\t<update tableName=\""+tableName+"\">" );
        for (int i = 1; i < columnSize; i++) {
            out.println( "\t\t<column name=\"" + map.get( i ) + "\" value=\"" + formatCell( row.getCell( i ) ) + "\"/>" );
        }
        out.println("\t\t<where>"+map.get(0)+"=\""+formatCell( row.getCell( 0) )+ "\"</where>");
        out.println( "\t</update>" );
    }
}
