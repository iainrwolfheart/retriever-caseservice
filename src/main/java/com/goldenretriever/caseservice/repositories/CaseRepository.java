package com.goldenretriever.caseservice.repositories;

import com.goldenretriever.caseservice.entities.Case;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends MongoRepository<Case, ObjectId> {
}
