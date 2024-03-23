package com.burcu.dto.response;

import com.burcu.utility.enums.Colour;
import com.burcu.utility.enums.Gender;
import com.burcu.utility.enums.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ColourResponseDto {

    private Colour colour;
    private String name;
    private String explanation;
    private Size size;
    private Gender gender;
}
