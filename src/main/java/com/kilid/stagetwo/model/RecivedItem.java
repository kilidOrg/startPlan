package com.kilid.stagetwo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecivedItem {
    private Integer property_type_id;
    private Integer no_bedrooms;
    private Integer no_bathrooms;
    private Float floor_area;
    private Integer age;
    private Integer pageNumber;
}
