package steps;

import controller.PetController;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import models.apiResponse.ApiResponse;
import models.apiResponse.PetResponse;
import org.apache.http.HttpStatus;
import models.pet.Pet;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static constants.PetConstans.GetPetIdResponseMessages.*;
import static constants.PetConstans.PostPetPetIdResponseMessages.*;

@RequiredArgsConstructor
public class PetSteps extends PetController {

    public PetSteps createPetSuccessfully(Pet pet) {
        Response response = postPet(pet);
        assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    public void postBadRequest(Object pet) {
        Response response = postPet(pet);
        assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
    }

    public Pet getPetById(String petId) {
        Response response = getPet(petId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return response.as(Pet.class);
    }

    public PetResponse getPetById2(String petId) {
        Response response = getPet(petId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return response.as(PetResponse.class);
    }

    public List<PetResponse> getPetByStatus(String petStatus) {
        Response response = getPetWithQueryParam(petStatus);
        assertStatusCode(HttpStatus.SC_OK, response);
        return Arrays.asList(response.then().extract().as(PetResponse[].class));
    }


    public void getNotFoundPetById(String petId) {
        Response response = getPet(petId);
        assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        assertErrorMessageFindByStatus(response.as(ApiResponse.class));
    }

    public void postPetPetId(String petId, String petName, String petStatus) {
        Response response = postPetWithFormData(petId, petName, petStatus);
        assertStatusCode(HttpStatus.SC_OK, response);
        assertMessagePostPetId(response.as(ApiResponse.class), petId);
        PetResponse response2 = getPetById2(petId);
        assertUpdatedPetByPetId(response2, petName, petStatus);
    }

    public void postPetNotFoundPetId(String petId) {
        Response response = postPetWithFormData(petId, "", "");
        assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        assertErrorMessagePostPetId(response.as(ApiResponse.class));
    }


    public PetSteps deletePetById(String petId) {
        Response response = deletePet(petId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    public PetSteps deleteNotFoundPetById(String petId) {
        Response response = deletePet(petId);
        assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        return this;
    }

    public PetSteps putPetSuccessfully(Pet pet) {
        Response response = putPet(pet);
        assertStatusCode(HttpStatus.SC_OK, response);
        assertPetData(pet);
        return this;
    }

    public PetSteps putBadRequest(Object pet) {
        Response response = putPet(pet);
        assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }


    private void assertStatusCode(int expectedStatus, Response response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus);
        softAssert.assertAll();
    }


    public void assertErrorMessageFindByStatus(ApiResponse apiResponse) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiResponse.getCode(), NOT_FOUND_CODE);
        softAssert.assertEquals(apiResponse.getType(), NOT_FOUND_TYPE);
        softAssert.assertEquals(apiResponse.getMessage(), NOT_FOUND_MESSAGE);
        softAssert.assertAll();
    }

    public void assertMessagePostPetId(ApiResponse apiResponse, String message) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiResponse.getCode(), SUCCESS_CODE);
        softAssert.assertEquals(apiResponse.getType(), UNKNOWN_TYPE);
        softAssert.assertEquals(apiResponse.getMessage(), message);
        softAssert.assertAll();
    }

    public void assertErrorMessagePostPetId(ApiResponse apiResponse) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiResponse.getCode(), NOT_FOUND_CODE_POST);
        softAssert.assertEquals(apiResponse.getType(), UNKNOWN_TYPE);
        softAssert.assertEquals(apiResponse.getMessage(), MESSAGE_NOT_FOUND);
        softAssert.assertAll();
    }

    public void assertUpdatedPetByPetId(PetResponse response, String name, String status) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), name);
        softAssert.assertEquals(response.getStatus().toString(), status);
        softAssert.assertAll();
    }

    public void assertPetStatuses(List statuses) {
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < statuses.size(); i++) {
            List<PetResponse> petList = getPetByStatus(statuses.get(i).toString());
            for (int j = 0; j < petList.size(); j++) {
                softAssert.assertEquals(petList.get(i).getStatus(), statuses.get(i));
            }
        }
        softAssert.assertAll();
    }

    public void assertPetData(Pet expectedPet) {
        SoftAssert softAssert = new SoftAssert();
        Pet pet = getPetById(expectedPet.getId().toString());
        softAssert.assertEquals(pet.getId(), expectedPet.getId());
        softAssert.assertEquals(pet.getCategory().getId(), expectedPet.getCategory().getId());
        softAssert.assertEquals(pet.getCategory().getName(), expectedPet.getCategory().getName());
        softAssert.assertEquals(pet.getName(), expectedPet.getName());
        softAssert.assertEquals(pet.getTags().get(0).getId(), expectedPet.getTags().get(0).getId());
        softAssert.assertEquals(pet.getTags().get(0).getName(), expectedPet.getTags().get(0).getName());
        softAssert.assertEquals(pet.getStatus(), expectedPet.getStatus());
        softAssert.assertAll();
    }

}
