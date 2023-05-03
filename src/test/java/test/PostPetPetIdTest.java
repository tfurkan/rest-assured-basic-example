package test;

import models.pet.Pet;
import org.testng.annotations.Test;
import steps.PetSteps;
import utils.TestDataGenerator;

import static constants.PetConstans.IncorrectData.INCORRECT_JSON;

public class PostPetPetIdTest  {

    private final PetSteps petSteps = new PetSteps();
    private final Pet minDataPet = TestDataGenerator.generateMinDataPet();
    private final Pet pet = TestDataGenerator.generatePetWithIdNameStatus();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();

    @Test
    public void successUpdatePost() {
        petSteps.createPetSuccessfully(minDataPet)
                .postPetPetId(minDataPet.getId().toString(), pet.getName(), pet.getStatus().toString());
    }

    @Test
    public void notFoundUpdate() {
        petSteps.postPetNotFoundPetId("-1");
    }


}

