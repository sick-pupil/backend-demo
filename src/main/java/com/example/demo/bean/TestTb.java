package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("test_tb")
public class TestTb extends Model<TestTb> implements Serializable {

    private static final long serialVersionUID = -5321232563659079010L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("db_id")
    private Long dbId;

    @TableField("tb_id")
    private Long tbId;

    @TableField("field1")
    private String field1;

    @TableField("field2")
    private LocalDateTime field2;

    @TableField("field3")
    private BigDecimal field3;
}
