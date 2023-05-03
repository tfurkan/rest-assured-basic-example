package test;

import models.pet.Pet;
import models.pet.Status;
import org.testng.annotations.Test;
import steps.PetSteps;
import utils.TestDataGenerator;

import java.util.Arrays;
import java.util.List;

public class GetPetFindByStatusTest  {
    private final PetSteps petSteps = new PetSteps();

    @Test
    public void getPetByStatus(){
        List<Status> statuses = Arrays.asList(Status.values());
        petSteps.assertPetStatuses(statuses);
    }
}
