package com.burcu.repository;

import com.burcu.dto.request.SizeAndGenderRequestDto;
import com.burcu.entity.Product;
import com.burcu.utility.enums.Colour;
import com.burcu.utility.enums.Gender;
import com.burcu.utility.enums.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //Beden ve cinsiyete göre ürünleri listeyen method.
    List<Product> findAllBySizeAndGender(Size size, Gender gender);

    //Bedene göre ürünleri listeleyen method.
    List<Product> findAllBySize(Size size);

    //Renk seçimine göre ürünleri listeleyen method.
    List<Product> findAllByColour(Colour colour);
}
