package com.goldenretriever.caseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@SpringBootApplication
public class CaseServiceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CaseServiceApplication.class, args);

		BufferedImage testImage = ImageIO.read(new java.io.File("/Users/goz/Documents/ONB/Merch/IMG-1355.JPG"));
		ByteArrayOutputStream imageAsOS = new ByteArrayOutputStream();
		ImageIO.write(testImage, "jpg", imageAsOS);
		byte[] bytes = imageAsOS.toByteArray();
		System.out.println(bytes.length);

/*	User enters case details, - DONE
	case is created and saved to db, - DONE
	caseId is returned - DONE
	moves to another page to add items, - N/A

	User enters item details before being prompted to upload images
	User clicks to upload images at which point create/item with name, desc & caseId added
	Return itemId - DONE
	As user uploads an image, image byte array will be stored temporarily in db along with itemId
	imageId is returned

	If image is subsequently deselected by user, trigger delete image request, passing imageId

	User confirms item / clicks to add another item, at which point
	Retrieve list of images from images table by itemId
 	For each image, add imageId string to item
 	Update item in table with populated imageId list

	Store returned image location from chosen storage solution in image entry in table

	Delete case
	- Get all items by caseId,
	- remove from chosen hosting,
	- remove from items table
	- remove case from cases table
*/
	}

}
