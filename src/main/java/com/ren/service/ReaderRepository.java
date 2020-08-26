package com.ren.service;

import com.ren.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author qiang.ren
 * @version 1.0
 * @since 2020/8/25 15:21
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
