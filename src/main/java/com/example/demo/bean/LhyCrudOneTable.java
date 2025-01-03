package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName lhy_crud_one_table
 */
@TableName(value ="lhy_crud_one_table")
@Data
public class LhyCrudOneTable implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "field1")
    private String field1;

    /**
     * 
     */
    @TableField(value = "field2")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate field2;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}