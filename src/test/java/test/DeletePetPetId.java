package test;

import models.pet.Pet;
import org.testng.annotations.Test;
import steps.PetSteps;
import utils.TestDataGenerator;

public class DeletePetPetId {
    private final PetSteps petSteps = new PetSteps();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();

    @Test
    public void deletePet() {
        petSteps.createPetSuccessfully(fullDataPet)
                .deletePetById(fullDataPet.getId().toString())
                .getNotFoundPetById(fullDataPet.getId().toString());
    }

    @Test
    public void deleteNotFoundPetTest() {
        petSteps.deleteNotFoundPetById("-1");
    }
}
