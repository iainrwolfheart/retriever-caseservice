package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Item;
import com.goldenretriever.caseservice.repositories.ItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private ImageService imageService;

    @Autowired
    public ItemService(ItemRepository itemRepository, ImageService imageService) {
        this.itemRepository = itemRepository;
        this.imageService = imageService;
    }

    /**
     * Replace Item w/ItemDto
     * No check for duplication by item name, currently
     *
     * @param newItem
     * @return
     */
    public ResponseEntity<String> createItem(Item newItem) {

        itemRepository.save(newItem);

        return ResponseEntity.status(HttpStatus.OK).body(newItem.get_itemId().toString());
    }

    /**
     * What happens when a case is deleted? Not sure this will suffice in long run...
     *
     * @param _itemId
     * @return
     */
    public ResponseEntity<String> removeItem(String _itemId) {
        try {
            imageService.removeItemImages(_itemId);
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Unable to remove Item.");
        }
        itemRepository.deleteById(new ObjectId(_itemId));
        return ResponseEntity.status(HttpStatus.OK).body("Item successfully REMOVED.");
    }

    /**
     * Currently very not performant as a repo.delete call is made for every item
     *      through the removeItem() method.
     *
     * @param _caseId
     */
    public void removeCaseItems(String _caseId) {
        List<Item> itemsToRemove = itemRepository.findBy_caseId(_caseId);

        for(Item item: itemsToRemove) {
            removeItem(item.get_itemId());
        }
    }
}
