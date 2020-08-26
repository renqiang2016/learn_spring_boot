package com.ren.service;

import com.ren.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qiang.ren
 * @version 1.0
 * @since 2020/8/25 9:53
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
