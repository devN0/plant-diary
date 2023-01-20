package com.plantdiary.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specimen {
    private long id;
    private long plantId;
    private String name;
    private float latitude;
    private float longitude;
}
