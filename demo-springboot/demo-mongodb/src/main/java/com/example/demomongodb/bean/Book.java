package com.example.demomongodb.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/11/23 9:25
 */
@Data
public class Book {
    private String name;
    private Date date;
    private Author author;

    /**
     * 克隆一个副本
     */
    public Book copy() {
        String json = JSON.toJSONString(this);
        return JSON.parseObject(json, Book.class);
    }

    public static void main(String[] args) {
        Book book = new Book();
        book.setName("龙城");
        book.setDate(new Date());
        Author author = new Author("方想", 20);
        book.setAuthor(author);

        Book copy = book.copy();
        copy.setName("龙城1");
        Author author1 = new Author("方想1,", 21);
        copy.setAuthor(author1);

        System.out.println(book);
        System.out.println(copy);
    }
}

@Data
class Author {
    private String name;
    private Integer age;

    public Author() {
    }

    public Author(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
