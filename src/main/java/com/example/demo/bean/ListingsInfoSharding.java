package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName listings_info
 */
@TableName(value ="listings_info")
@Data
public class ListingsInfoSharding implements Serializable {
    /**
     * 
     */
    @TableField(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "host_id")
    private Long hostId;

    /**
     * 
     */
    @TableField(value = "host_name")
    private String hostName;

    /**
     * 
     */
    @TableField(value = "neighbourhood_group")
    private String neighbourhoodGroup;

    /**
     * 
     */
    @TableField(value = "neighbourhood")
    private String neighbourhood;

    /**
     * 
     */
    @TableField(value = "latitude")
    private BigDecimal latitude;

    /**
     * 
     */
    @TableField(value = "longitude")
    private BigDecimal longitude;

    /**
     * 
     */
    @TableField(value = "room_type")
    private String roomType;

    /**
     * 
     */
    @TableField(value = "price")
    private Long price;

    /**
     * 
     */
    @TableField(value = "minimum_nights")
    private Long minimumNights;

    /**
     * 
     */
    @TableField(value = "number_of_reviews")
    private Long numberOfReviews;

    /**
     * 
     */
    @TableField(value = "last_review")
    private LocalDate lastReview;

    /**
     * 
     */
    @TableField(value = "reviews_per_month")
    private BigDecimal reviewsPerMonth;

    /**
     * 
     */
    @TableField(value = "calculated_host_listings_count")
    private Long calculatedHostListingsCount;

    /**
     * 
     */
    @TableField(value = "availability_365")
    private Long availability365;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}