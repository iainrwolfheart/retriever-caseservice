package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Image;
import com.goldenretriever.caseservice.entities.dto.ImageDto;
import com.goldenretriever.caseservice.repositories.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService<T extends ImageStorageService> {

    private ImageRepository imageRepository;
    private ImageStorageService storageService;

    public ImageService(ImageRepository imageRepository,
                        StorageServiceFactory storageServiceFactory) {
        this.imageRepository = imageRepository;
        this.storageService = storageServiceFactory.create("Local");
    }

    /**
     * Receives request from ImageController.
     * Uses saveImageDetailsToDB() method to communicate with ImageRepository() before creating appropriate ResponseEntity.
     *
     * @param imageDto data transfer object, comprising Multipart file (the image) and corresponding _itemId to which the
     *       image is linked.
     * @return ResponseStatus 200 indicating successful saving of Image details to database OR 422
     *       if something goes wrong.
     */
    public ResponseEntity<String> presaveImage(ImageDto imageDto) {
        try {
            String _imageId = saveImageDetailsToDB(imageDto);
            return ResponseEntity.status(HttpStatus.OK).body(_imageId);
        } catch (IOException ioe) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("There was a problem saving this image... " +
                            "Either it already exists or there was another issue saving it.");
        }
    }

    /**
     * Stores image, including it's binary, to MongoDB table. This is done as the user is uploading images for an item.
     *      Once the user confirms the item, the item images are all saved in their original format to the desired
     *      solution (see saveItemImagesToStorage).
     *
     * Currently no obvious way to check if image already exists in the table.
     *      Searching by binaryImage of the imageToSave returns an empty list...
     *
     * @param imageDto data transfer object, comprises Multipart File and related _itemId
     * @return stringified _imageId, auto-created as it's saved in MongoDB
     * @throws IOException - May be inappropriate
     */
    public String saveImageDetailsToDB(ImageDto imageDto) throws IOException {
        Image imageToSave = new Image(imageDto.get_itemId(), imageDto.getImage().getBytes());
        imageRepository.save(imageToSave);
        return imageToSave.get_imageId().toString();
    }

    public ResponseEntity<String> removePresavedImageFromDB(String _imageId) {
        imageRepository.deleteBy_imageId(_imageId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Image removed successfully.");
    }

    public ResponseEntity<String> saveItemImages(String _caseId, String _itemId) {
        List<Image> itemImagesToStoreToDB = imageRepository.findBy(_itemId);

        try {
            storageService.saveItemImagesToStorage(itemImagesToStoreToDB, _caseId, _itemId);
        } catch (IOException ioe) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Unable to store item images to long term storage. Please try again.");

        }

        return ResponseEntity.status(HttpStatus.OK).body("Appropriate response message here, please.");
    }


    /**
     * Called by ItemService.removeItem() when an item is to be removed from a Case, either because the case has been
     *      resolved, or a specific item has been found or mistakenly declared as part of the Case.
     *
     * Calls the storageService to remove all the images.
     * Then removes all the Images' details from the DB
     *
     * @param _itemId
     */
    public void removeItemImages(String _itemId) throws IOException{
            storageService.deleteAll(_itemId);
            imageRepository.deleteBy_itemId(_itemId);
    }
}
