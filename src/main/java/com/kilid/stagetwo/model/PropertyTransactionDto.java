package com.kilid.stagetwo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PropertyTransactionDto {
    private Integer id;
    private String    uprn;
    private  Integer landuse_type_id;
    private Integer property_type_id;
    private  Integer no_bedrooms;
    private  Integer no_bathrooms;
    private  Float floor_area;
    private  Integer floor_area_metric_id;
    private  Integer age;
    private Date date;
    private  Integer currency_metric_id;
    private  Float price_metric1;
    private  Float price_metric2;
}
