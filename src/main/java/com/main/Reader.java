package com.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.main.Image;


public class Reader {
	public static void setUp() {
		try (OutputStream output = new FileOutputStream("config.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("image.folder", "pics");
            prop.setProperty("archive.folder","archive");
            prop.setProperty("xls.name","Empsheet.xlsx");
            prop.setProperty("output.file","EmpCol.txt");
            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }
		
	}
	public static void main(String[] args) {
		// ConfigurableApplicationContext context = SpringApplication.run(Reader.class, args);
			System.out.println( "BED" );
		setUp();
		Image.writeImage("yurop 661.jpg", "castle.jpg");
		Json.ReadJson("yurop 661.jpg");
	
		
	
	}
	
}
