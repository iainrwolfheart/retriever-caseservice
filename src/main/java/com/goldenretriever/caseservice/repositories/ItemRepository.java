package com.goldenretriever.caseservice.repositories;

import com.goldenretriever.caseservice.entities.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {
}
