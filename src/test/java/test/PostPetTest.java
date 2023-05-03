package test;

import org.testng.annotations.Test;
import models.pet.Pet;
import steps.PetSteps;
import utils.TestDataGenerator;

import static constants.PetConstans.IncorrectData.INCORRECT_JSON;

public class PostPetTest  {

    private final PetSteps petSteps = new PetSteps();
    private final Pet minDataPet = TestDataGenerator.generateMinDataPet();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();

    @Test
    public void createMinDataPet() {
        petSteps.createPetSuccessfully(minDataPet);
    }

    @Test
    public void createFullDataPet() {
        petSteps.createPetSuccessfully(fullDataPet);
    }

    @Test
    public void postIncorrectJson() {
        petSteps.postBadRequest(INCORRECT_JSON);
    }
}
