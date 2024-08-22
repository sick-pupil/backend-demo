package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName reviews_info
 */
@TableName(value ="reviews_info")
@Data
public class ReviewsInfo implements Serializable {
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}