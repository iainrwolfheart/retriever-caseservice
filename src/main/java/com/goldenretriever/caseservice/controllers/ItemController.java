package com.goldenretriever.caseservice.controllers;

import com.goldenretriever.caseservice.entities.Item;
import com.goldenretriever.caseservice.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createItem(@RequestBody Item newItem) {
        return itemService.createItem(newItem);
    }

    //    delete item and remove all images
    @DeleteMapping(value = "/remove/{_itemId}")
    public ResponseEntity<String> removeItem(@PathVariable(value = "_itemId") String _itemId) {
        return itemService.removeItem(_itemId);
    }
}
