package com.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

public class Image {
	public static void ifImageExists(String outputImageName, File outputFile,Properties prop,BufferedImage image ) throws IOException {
		if(outputFile.exists()) {
			 System.out.println("File exists");
			 File outputFile2 = new File(prop.getProperty("archive.folder") + "\\" + outputImageName);
			//Files.move(outputFile.toPath(), Paths.get(prop.getProperty("archive.folder")), StandardCopyOption.REPLACE_EXISTING);
			 if(!outputFile2.getParentFile().exists()){
			        outputFile2.getParentFile().mkdirs();
			    }
			 ImageIO.write(image, "JPEG", outputFile2);
			 outputFile.delete();
		 }
	}
	public static void writeImage(String inputImageName,String outputImageName) {
		File imageFile = new File(inputImageName);
		try(InputStream input = new FileInputStream("config.properties")){
			
			BufferedImage image = ImageIO.read(imageFile);
		    Properties prop = new Properties();
		    prop.load(input);
		    System.out.println(prop.getProperty("image.folder"));
		    
			File outputFile = new File(prop.getProperty("image.folder") + "\\" + outputImageName);
			 if(!outputFile.getParentFile().exists()){
			        outputFile.getParentFile().mkdirs();
			    }
			 
			 //HANDLES CASE WHERE IMAGE EXISTS IN FOLDER ALREADY.
			 ifImageExists(outputImageName,outputFile,prop,image);
			
			ImageIO.write(image, "JPEG", outputFile);
			System.out.println("seda");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	

}
