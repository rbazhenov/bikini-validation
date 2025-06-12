package org.example;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoConnectionApplicationLiveTest {
    private static final String HOST = "localhost";
    private static final String PORT = "27017";
    private static final String DB = "test";
    private static final String USER = "admin";
    private static final String PASS = "admin";

    @Test
    public void whenPropertiesConfig_thenInsertSucceeds() {
        System.setProperty("spring.data.mongodb.host", HOST);
        System.setProperty("spring.data.mongodb.port", PORT);
        System.setProperty("spring.data.mongodb.database", DB);
        System.setProperty("spring.data.mongodb.username", USER);
        System.setProperty("spring.data.mongodb.password", PASS);

        SpringApplicationBuilder app = new SpringApplicationBuilder(Application.class)
                .properties(
                        "spring.data.mongodb.host=oldValue",
                        "spring.data.mongodb.port=oldValue",
                        "spring.data.mongodb.database=oldValue",
                        "spring.data.mongodb.username=oldValue",
                        "spring.data.mongodb.password=oldValue"
                );
        app.run();

        assertInsertSucceeds(app.context());
    }

    private void assertInsertSucceeds(ConfigurableApplicationContext context) {
        String name = "A";

        MongoTemplate mongo = context.getBean(MongoTemplate.class);
        Document doc = Document.parse("{\"name\":\"" + name + "\"}");
        Document inserted = mongo.insert(doc, "items");

        assertNotNull(inserted.get("_id"));
        assertEquals(inserted.get("name"), name);
    }
}
