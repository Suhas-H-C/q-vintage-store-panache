package org.shc.quarkus.panache.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;

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
    void shouldThrowBadRequestWhenUnknownContextIsPassed() {
        given()
                .when()
                .get("/v1/unknown")
                .then()
                .statusCode(404);
    }
}