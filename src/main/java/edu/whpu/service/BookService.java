package edu.whpu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.whpu.entity.Book;
import edu.whpu.mapper.BookMapper;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Tool(description = "根据书名查询书籍价格")
    public BigDecimal getPriceByName(String bookName) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<Book>().eq("book_name", bookName);
        return bookMapper.selectOne(queryWrapper).getBookPrice();
    }
}
