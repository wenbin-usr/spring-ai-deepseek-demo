package edu.whpu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @TableId
    private Long bookId;

    private String bookName;

    private String bookAuthor;

    private BigDecimal bookPrice;

    private String bookPublisher;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;


}
