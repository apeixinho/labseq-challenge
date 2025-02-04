package org.labseq;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LabSeqResourceTest {

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 1",
        "2, 0",
        "3, 1",
        "4, 1",
        "5, 1",
        "6, 1",
        "7, 2",
        "8, 2",
        "9, 2",
        "10, 3"
    })
    void testSequenceValues(int input, String expected) {
        given()
            .when().get("/api/v1/labseq/" + input)
            .then()
            .statusCode(200)
            .body(is(expected));
    }

    @Test
    void testInvalidNegativeInput() {
        given()
            .when().get("/api/v1/labseq/-1")
            .then()
            .statusCode(400);
    }

    @Test
    void testInvalidLargeInput() {
        given()
            .when().get("/api/v1/labseq/20001")
            .then()
            .statusCode(400);
    }

    @Test
    void testLargeValidSequence() {
        given()
            .when().get("/api/v1/labseq/1500")
            .then()
            .statusCode(200)
            .body(is("3431224141950985531562782253357633230397697407815277458523864211188471132581062869460013609339562423429942275179714875904872075640"));
    }
}
