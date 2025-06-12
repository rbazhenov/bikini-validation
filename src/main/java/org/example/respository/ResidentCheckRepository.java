package org.example.respository;

import org.example.entity.ResidentCheckEntity;
import org.example.model.CheckResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResidentCheckRepository extends MongoRepository<ResidentCheckEntity, String> {
    ResidentCheckEntity findByInternalId(String internalId);

    List<ResidentCheckEntity> findByResult(CheckResult result);
}
