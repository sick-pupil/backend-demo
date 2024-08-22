package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * 
 * @TableName calendar
 */
@TableName(value ="calendar")
@Data
public class CalendarSharding implements Serializable {
    /**
     * 
     */
    @TableField(value = "listing_id")
    private Long listingId;

    /**
     * 
     */
    @TableField(value = "date")
    private LocalDate date;

    /**
     * 
     */
    @TableField(value = "available")
    private String available;

    /**
     * 
     */
    @TableField(value = "price")
    private String price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}