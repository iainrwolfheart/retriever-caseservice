package com.goldenretriever.caseservice.repositories;

import com.goldenretriever.caseservice.entities.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    @Query(value = "{ '_id': '0?' }")
    Item findBy(String _itemId);

}
