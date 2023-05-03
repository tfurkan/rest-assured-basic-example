package constants;

public class PetConstans {
    public static class GetPetIdResponseMessages{
        public static final Integer NOT_FOUND_CODE = 1;
        public static final String NOT_FOUND_TYPE = "error";
        public static final String NOT_FOUND_MESSAGE = "Pet not found";


    }
    public static class PostPetPetIdResponseMessages{
        public static final Integer SUCCESS_CODE = 200;
        public static final String UNKNOWN_TYPE = "unknown";
        public static final Integer NOT_FOUND_CODE_POST = 404;
        public static final String MESSAGE_NOT_FOUND = "not found";


    }
    public static class IncorrectData{
        public static final String INCORRECT_JSON = "{ \"id\": incorrectValue}";
    }
}
