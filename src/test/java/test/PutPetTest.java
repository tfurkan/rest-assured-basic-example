package test;

import constants.PetConstans;
import models.pet.Pet;
import org.testng.annotations.Test;
import steps.PetSteps;
import utils.TestDataGenerator;

import static constants.PetConstans.IncorrectData.INCORRECT_JSON;

public class PutPetTest {
    private final PetSteps petSteps = new PetSteps();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();
    private final Pet modifiedPet = fullDataPet.toBuilder().name("SayMyName").build();

    @Test
    public void updateFullDataPet() {
        petSteps.createPetSuccessfully(fullDataPet)
                .putPetSuccessfully(modifiedPet)
                .assertPetData(modifiedPet);
    }
    @Test
    public void putNewPet() {
        petSteps.putPetSuccessfully(fullDataPet).assertPetData(fullDataPet);
    }

    @Test
    public void putIncorrectJson() {
        petSteps.putBadRequest(INCORRECT_JSON);
    }
}
