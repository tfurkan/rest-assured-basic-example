package utils;

import org.apache.commons.lang3.RandomStringUtils;
import models.pet.Category;
import models.pet.Pet;
import models.pet.Status;
import models.pet.Tag;

import java.util.Arrays;
import java.util.Random;

public class TestDataGenerator {
    public static Pet generateFullDataPet() {
        return Pet.builder()
                .id(getRandomInt())
                .name(getRandomString())
                .photoUrls(Arrays.asList(getRandomString(), getRandomString()))
                .category(Category.builder().id(getRandomInt()).name(getRandomString()).build())
                .tags(Arrays.asList(Tag.builder().id(getRandomInt()).name(getRandomString()).build(),
                        Tag.builder().id(getRandomInt()).name(getRandomString()).build()))
                .status(Status.available)
                .build();
    }
    public static Pet test() {
        return Pet.builder()
                .id(getRandomInt())
                .name(getRandomString())
                .photoUrls(Arrays.asList(getRandomString(), getRandomString()))
                .category(Category.builder().id(getRandomInt()).name(getRandomString()).build())
                .tags(Arrays.asList(Tag.builder().id(getRandomInt()).name(getRandomString()).build(),
                        Tag.builder().id(getRandomInt()).name(getRandomString()).build()))
                .status(Status.available)
                .build();
    }

    public static Pet generateMinDataPet() {
        return Pet.builder()
                .id(getRandomInt())
                .name(getRandomString())
                .build();
    }
    public static Pet generatePetWithIdNameStatus() {
        return Pet.builder()
                .name(getRandomString())
                .status(Status.pending)
                .build();
    }


    private static Integer getRandomInt() {
        return new Random().nextInt((65536) - 32768);
    }

    private static String getRandomString() {
        return RandomStringUtils.randomAlphabetic(7);
    }
}
