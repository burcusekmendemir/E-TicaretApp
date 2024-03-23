package com.burcu.dto.request;

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
public class SizeAndGenderRequestDto {
    private Size size;
    private Gender gender;
}
