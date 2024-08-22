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
 * @TableName reviews_detail_info
 */
@TableName(value ="reviews_detail_info")
@Data
public class ReviewsDetailInfoSharding implements Serializable {
    /**
     * 
     */
    @TableField(value = "listing_id")
    private Long listingId;

    /**
     * 
     */
    @TableField(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "date")
    private LocalDate date;

    /**
     * 
     */
    @TableField(value = "reviewer_id")
    private Long reviewerId;

    /**
     * 
     */
    @TableField(value = "reviewer_name")
    private String reviewerName;

    /**
     * 
     */
    @TableField(value = "comments")
    private String comments;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}