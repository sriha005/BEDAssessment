package com.main;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Json {
	
	@SuppressWarnings("unchecked")
	public static void ReadJson(String imageFileName) 
    {
		File image = new File(imageFileName);
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
      //Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        //Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet( " Employee Info ");

        //Create row object
        XSSFRow row;
        
      
        
      //This data needs to be written (Object[])
       ArrayList< Object[] > empinfo = new ArrayList <Object[] >();
        empinfo.add(new Object[] {
           "firstName", "lastName", "dob" });
         
       // read JSON with person/employee
        try (FileReader reader = new FileReader("person.json"))
        {
        	//properties file
        	InputStream input = new FileInputStream("config.properties");
        	Properties prop = new Properties();
        	prop.load(input);
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            //output to text file the JSON content
            FileWriter myWriter = new FileWriter(prop.getProperty("output.file"));
            myWriter.write( "firstName | " + "lastName | "+ "dob\n");
            
            
            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
             
            //Iterate over employee array
            employeeList.forEach(emp -> {
				try {
					parsePersonObject( (JSONObject) emp , empinfo, myWriter);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} );
            
            //write image name, size and employee list to json file
            writeJson(image,employeeList);
            
            //for excel sheet
            int rowid = 0;
            
            for (Object[] objectArr : empinfo) {
                row = spreadsheet.createRow(rowid++);
                
                int cellid = 0;
                
                for (Object obj2 : objectArr){
                   Cell cell = row.createCell(cellid++);
                   cell.setCellValue((String)obj2);
                }
             }
            
             //Write the workbook in file system
             FileOutputStream out = new FileOutputStream(
                new File(prop.getProperty("xls.name")));
             
             workbook.write(out);
             out.close();
             myWriter.close();
             System.out.println("Writesheet.xlsx written successfully");
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        
        
    }
	
	private static void parsePersonObject(JSONObject person, ArrayList<Object[]> empinfo, FileWriter myWriter) throws IOException 
    {
        //Get employee object within list
        JSONObject personObject = (JSONObject) person.get("person");
         
        //Get employee first name
        String firstName = (String) personObject.get("firstName");    
        System.out.println(firstName);
        if(!isStringOnlyAlphabet(firstName)) {
        	firstName = "Invalid - Non Alphabet";
        }
        //Get employee last name
        String lastName = (String) personObject.get("lastName");  
        System.out.println(lastName);
        if(!isStringOnlyAlphabet(lastName)) {
        	lastName = "Invalid - Non Alphabet";
        }
         
        //Get employee birthday
        String dob = (String) personObject.get("dob");    
        System.out.println(dob);
        if(!(isValid(dob))) {
        	dob = "Invalid - Format";
        }
        empinfo.add(new Object[] {
                firstName, lastName, dob });
        myWriter.write( firstName + " | " + lastName + " | "+  dob + "\n");
    }
	

    @SuppressWarnings("unchecked")
    public static void writeJson(File image,JSONArray employeeList)
    {
        // Employee List
        JSONObject imageEmployee  = new JSONObject();
        imageEmployee.put("imageName", image.getName());
        imageEmployee.put("imageSize", image.length());
        imageEmployee.put("employees", employeeList);
         
        JSONObject employeeObject = new JSONObject(); 
        employeeObject.put("imageEmployee", imageEmployee);
         
       
       
        //Add employees to list
        JSONArray imageEmployeeList = new JSONArray();
        imageEmployeeList.add(employeeObject);
        
        //Write JSON file
        try (FileWriter file = new FileWriter("imageEmployees.json")) {
 
            file.write(imageEmployeeList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isValid(String dateStr) {
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    		    
        try {
            dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
    public static boolean isStringOnlyAlphabet(String str) 
    { 
        return ((str != null) 
                && (!str.equals("")) 
                && (str.matches("^[a-zA-Z]*$"))); 
    } 
    
    
}

