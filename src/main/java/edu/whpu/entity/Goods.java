package edu.whpu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("goods")
@Data
public class Goods {
    @TableId
    private Long id;

    private String goodsName;

    private BigDecimal goodsPrice;

    private String goodsDesc;
}