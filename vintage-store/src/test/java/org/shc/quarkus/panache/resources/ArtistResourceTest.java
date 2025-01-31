package org.shc.quarkus.panache.resources;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class ArtistResourceTest {

    @Test
    void shouldReturnAllArtist() {
        given()
                .when()
                .get("/v1/artist")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("$", empty());
    }

    @Test
    void shouldReturnArtistById() {
        given()
                .when()
                .get("/v1/artist/1")
                .then()
                .log()
                .all()
                .statusCode(204);
    }

    @Test
    void shouldThrowBadRequestWhenUnknownContextIsPassed() {
        given()
                .when()
                .get("/v1/unknown")
                .then()
                .statusCode(404);
    }

    @Test
    @TestTransaction
    void shouldPersistAnArtistAndReturnTheSame() {
        String requestBody = """
                    {
                        "bio": "Persisting the developer as an artist",
                        "name": "Suhas H C"
                    }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/v1/artist")
                .then()
                .statusCode(201)
                .body("name", equalTo("Suhas H C"))
                .body("bio", equalTo("Persisting the developer as an artist"));
    }
}