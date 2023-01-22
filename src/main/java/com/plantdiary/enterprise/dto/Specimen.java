package com.plantdiary.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specimen {
    private long id;
    private long plantId;
    @NotBlank
    private String name;
    private float latitude;
    private float longitude;
}
