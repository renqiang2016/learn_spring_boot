package com.ren.controller;

import com.ren.model.AmazonProperties;
import com.ren.model.Book;
import com.ren.service.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author qiang.ren
 * @version 1.0
 * @since 2020/8/25 9:56
 */
@Controller
@RequestMapping("/readingList")
public class ReadingListController {

    private AmazonProperties amazonProperties;
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(AmazonProperties amazonProperties, ReadingListRepository readingListRepository) {
        this.amazonProperties = amazonProperties;
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String getReadingListByReader(@PathVariable("reader") String reader,
                                         Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAmazonId());
        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader,
                                   Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList/{reader}";
    }
}
