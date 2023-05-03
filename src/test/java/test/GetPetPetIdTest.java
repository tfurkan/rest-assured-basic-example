package test;

import models.pet.Pet;
import org.testng.annotations.Test;
import steps.PetSteps;
import utils.TestDataGenerator;


public class GetPetPetIdTest  {
    private final PetSteps petSteps = new PetSteps();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();

    @Test
    public void getPetById(){
        petSteps.createPetSuccessfully(fullDataPet).assertPetData(fullDataPet);
    }
    @Test
    public void getNotFoundPetTest(){
        petSteps.getNotFoundPetById("-1");
    }
}
