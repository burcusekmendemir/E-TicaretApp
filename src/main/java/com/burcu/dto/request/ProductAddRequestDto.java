package com.burcu.dto.request;


import com.burcu.utility.enums.Colour;
import com.burcu.utility.enums.Gender;
import com.burcu.utility.enums.Size;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductAddRequestDto {

    private String name;
    private String explanation;
    private Double price;
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Size size;
    @Enumerated(EnumType.STRING)
    private Colour colour;

    private String pattern;
    private String material;
    private String environment;
    private String fabricType;
    private String height;
    
    private List<ProductAndModel> models;
    private List<ProductAndBrand> brands;
    private List<ProductAndCategory> categories;
    private List<ProductAndImage> images;



    @Data
    public static class ProductAndModel{
        private Long id;
        private String name;
    }
    @Data
    public static class ProductAndBrand{
        private Long id;
        private String name;
    }

    @Data
    public static class ProductAndCategory{
        private Long id;
        private String name;
    }
    @Data
    public static class ProductAndImage{
        private Long id;
        private String url;
    }

}