package org.example;


import static org.assertj.core.api.Assertions.assertThat;

import org.example.entity.ResidentCheckEntity;
import org.example.model.CheckResult;
import org.example.respository.ResidentCheckRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestPropertySource(properties = {"logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG"})
class ResidentCheckRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ResidentCheckRepository repository;

    @Test
    public void mongoTemplateSaveTest() {
        ResidentCheckEntity entity = createEntity();
        String collectionName1 = "collectionName1";
        mongoTemplate.save(entity, collectionName1);

        assertThat(mongoTemplate.findAll(ResidentCheckEntity.class, collectionName1))
                .extracting("result")
                .containsOnly(CheckResult.SUCCESS);
    }

    @Test
    public void mongoRepositorySaveTest() {
        ResidentCheckEntity entity = createEntity();
        ResidentCheckEntity entity2 = createEntity();
        repository.saveAll(Arrays.asList(entity, entity2));

        assertThat(repository.findByResult(CheckResult.SUCCESS)).hasSize(2);
    }

    private ResidentCheckEntity createEntity() {
        final String internalId = "internalId";
        ResidentCheckEntity entity = new ResidentCheckEntity();
        entity.setResult(CheckResult.SUCCESS);
        entity.setInternalId(internalId);
        return entity;
    }
}
