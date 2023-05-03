package controller;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public abstract class PetController {
    protected static utils.Configuration config = new utils.Configuration();
    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setBaseUri(config.getProperty("BASE_URI"))
            .setBasePath(config.getProperty("BASE_PATH"))
            .setContentType(ContentType.JSON)
            .addHeader("api_key", "12345")
            .build();


    public Response getPet(String petId) {
        return given(requestSpecification)
                .urlEncodingEnabled(false)
                .log().method()
                .log().uri()
                .log().body()
                .when()
                .get(petId)
                .then()
                .log().body()
                .extract().response();
    }


    public Response getPetWithQueryParam(String path) {
        return RestAssured.given(requestSpecification)
                .queryParam("status", path)
                .when()
                .get("/findByStatus");
    }

    public Response postPet(Object pet) {
        return RestAssured.given(requestSpecification)
                .when()
                .body(pet)
                .post();
    }

    public Response postPetWithFormData(String path, String formParam, String formParam2) {
        return given().log().method().log().uri().log().headers().log().body()
                .baseUri(config.getProperty("BASE_URI"))
                .basePath(config.getProperty("BASE_PATH"))
                .contentType(ContentType.URLENC)
                .when()
                .formParam("name", formParam)
                .formParam("status", formParam2)
                .post(path).then().extract().response();
    }

    public Response putPet(Object pet) {
        return RestAssured.given(requestSpecification)
                .when()
                .body(pet)
                .put();
    }

    public Response deletePet(String petId) {
        return RestAssured.given(requestSpecification)
                .when()
                .delete(petId);
    }
}
