-- db_test01.book definition

CREATE TABLE `book` (
    `book_id` bigint NOT NULL AUTO_INCREMENT,
    `book_name` varchar(100)  DEFAULT NULL,
    `book_author` varchar(100) DEFAULT NULL,
    `book_price` double DEFAULT NULL,
    `book_publisher` varchar(100) DEFAULT NULL,
    `version` int NOT NULL DEFAULT '0',
    `deleted` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`book_id`),
    UNIQUE KEY `book_name_unique` (`book_name`)
) ENGINE=InnoDB;


INSERT INTO db_test01.book (book_name,book_author,book_price,book_publisher,version,deleted) VALUES
 ('数据结构与算法','严蔚敏',36.55,'清华大学出版社',0,0),
 ('Java并发编程的艺术','方腾飞',43.78,'机械工业出版社',2,0),
 ('C语言程序设计','程皓',52.56,'北京大学出版社',1,0),
 ('Redis设计与实现','武胜',99.85,'清华大学出版社',0,0),
 ('深入理解Java虚拟机','周志明',56.88,'武汉大学出版社',0,0),
 ('深入理解Linux网络','张彦飞',37.95,'电子工业出版社',0,0),
 ('图解HTTP','崔斌',47.28,'人民邮电出版社',0,0),
 ('高性能MySQL','陈雷',63.74,'机械工业出版社',0,0),
 ('Java新特性','李浩',37.98,'北京理工大学出版社',0,0);