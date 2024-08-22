package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName neighbourhood
 */
@TableName(value ="neighbourhood")
@Data
public class Neighbourhood implements Serializable {
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}