package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("test_dict_tb")
public class TestDictTb extends Model<TestDictTb> implements Serializable {

    private static final long serialVersionUID = -1885703989822504548L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("dict_type")
    private String dictType;

    @TableField("dict_key")
    private String dictKey;

    @TableField("dict_value")
    private String dictValue;
}
