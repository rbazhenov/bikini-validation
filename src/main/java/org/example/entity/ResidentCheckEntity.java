package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.example.model.CheckResult;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("ResidentCheck")
public class ResidentCheckEntity {

    @Id
    private String id;

    private String internalId;
    private CheckResult result;
}
