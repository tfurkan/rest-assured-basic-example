package models.apiResponse;

import lombok.*;
import models.pet.Status;

import java.util.List;
@Getter
public class PetResponse extends ApiResponse {


        private Long id;
        private Category category;
        private String name;
       // private List<PhotoUrls> photoUrls;
        private List<Tags> tags;
        private Status status;
        @Getter
        public static class Category {
            private Long id;
            private String name;
        }

       /* public static class PhotoUrls{
            private String photoUrls;
        }*/
    @Getter
        public static class Tags{
            private Long id;
            private String name;
        }

}
