package com.kilid.stagetwo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
//@Entity(name = "property_transaction_10")
//@Table(name = "property_transaction_10" , schema = "public")
public class PropertyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "market_partition_id")
    private Integer market_partition_id;
    @Column(name = "property_id")
    private Integer property_id;
    @Column(name = "uprn")
    private String uprn;
    @Column(name = "uprn_type_id")
    private Integer uprn_type_id;
    @Column(name = "landuse_type_id")
    private Integer landuse_type_id;
    @Column(name = "property_type_id")
    private Integer property_type_id;
    @Column(name = "structure_type_id")
    private Integer structure_type_id;
    @Column(name = "no_bedrooms")
    private Integer no_bedrooms;
    @Column(name = "no_parkings")
    private Integer no_parkings;
    @Column(name = "no_bathrooms")
    private Integer no_bathrooms;
    @Column(name = "no_rooms")
    private  Integer no_rooms;
    @Column(name = "floor_area")
    private Float floor_area;
    @Column(name = "floor_area_metric_id")
    private Integer floor_area_metric_id;
    @Column(name = "year_built")
    private Integer year_built;
    @Column(name = "age")
    private Integer age;
    @Column(name = "new_built")
    private Boolean new_built;
    @Column(name = "date")
    private Date date;
    @Column(name = "period_id")
    private Integer period_id;
    @Column(name = "currency_metric_id")
    private Integer currency_metric_id;
    @Column(name = "transaction_type_id")
    private Integer transaction_type_id;
    @Column(name = "price_metric1")
    private Float price_metric1;
    @Column(name = "price_metric2")
    private Float price_metric2;
    @Column(name = "surface_geom")
    private String surface_geom;
    @Column(name = "point_geom")
    private String point_geom;
    @Column(name = "property_location_type_id")
    private Integer property_location_type_id;
    @Column(name = "location_id")
    private Integer location_id;
    @Column(name = "location_local_id")
    private String location_local_id;
    @Column(name = "location_hierarchy")
    private Integer location_hierarchy;
    @Column(name = "more_info")
    private String more_info;
    @Column(name = "anomaly_detected")
    private Boolean anomaly_detected;
    @Column(name = "anomaly_type_ids")
    private Integer anomaly_type_ids;

}
