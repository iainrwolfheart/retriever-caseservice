package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Item;
import com.goldenretriever.caseservice.repositories.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ResponseEntity<String> createItem(Item newItem) {

        itemRepository.save(newItem);

        return ResponseEntity.status(HttpStatus.OK).body(newItem.get_itemId().toString());
    }
}
