package com.burcu.dto.response;

import com.burcu.utility.enums.Gender;
import com.burcu.utility.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeAndGenderFindAllResponseDto {

    private Size size;
    private Gender gender;
    private String name;
    private String explanation;
    private Double price;
}
