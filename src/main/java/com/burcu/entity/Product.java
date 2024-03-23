package com.burcu.entity;

import com.burcu.dto.request.ProductAddRequestDto;
import com.burcu.utility.enums.Colour;
import com.burcu.utility.enums.Gender;
import com.burcu.utility.enums.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String explanation;
    private Double price;
    private Integer stock;

   private Long brandId;
   private Long categoryId;
   private Long modelId;

   @OneToOne
   @JoinColumn(name = "main_image_id")
   private Image mainImage;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
   private List<Image> sideImages;

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

}