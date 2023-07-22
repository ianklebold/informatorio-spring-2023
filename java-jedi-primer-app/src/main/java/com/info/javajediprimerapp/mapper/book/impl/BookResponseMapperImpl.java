package com.info.javajediprimerapp.mapper.book.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.domain.Review;
import com.info.javajediprimerapp.mapper.book.BookResponseMapper;
import com.info.javajediprimerapp.mapper.category.CategoryResponseMapper;
import com.info.javajediprimerapp.mapper.publisher.PublisherMapper;
import com.info.javajediprimerapp.mapper.review.ReviewMapper;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;
import com.info.javajediprimerapp.model.dto.category.CategoryResponseDTO;
import com.info.javajediprimerapp.model.dto.publisher.PublisherDTO;
import com.info.javajediprimerapp.model.dto.review.ReviewDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BookResponseMapperImpl implements BookResponseMapper {

    private final CategoryResponseMapper categoryResponseMapper;

    private final PublisherMapper publisherMapper;

    private final ReviewMapper reviewMapper;

    @Override
    public BookResponseDTO bookToBookResponseDTO(Book book) {

        BookResponseDTO bookResponseDTO = BookResponseDTO.builder()
                .nombreAuthor(book.getAuthor().getName())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .numberPages(book.getNumberPages())
                .build();

        bookResponseDTO.setCategoryResponseDTOS(getCategoryDTOS(book.getCategories()));
        bookResponseDTO.setPublisherDTO(publisherMapper.publisherToPublisherDTO(book.getPublisher()));
        bookResponseDTO.setReviews(getReviewsDTOS(book.getReviews()));

        return bookResponseDTO;
    }

    private List<CategoryResponseDTO> getCategoryDTOS(List<Category> categories){
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();

        for (Category category:categories) {
            categoryResponseDTOS.add(categoryResponseMapper.categoryToCategoryResponseDTO(category));
        }
        return categoryResponseDTOS;
    }

    private List<ReviewDTO> getReviewsDTOS(List<Review> reviews){
        List<ReviewDTO> reviewDTOS = new ArrayList<>();

        for (Review review:reviews) {
            reviewDTOS.add(reviewMapper.reviewToReviewDTO(review));
        }
        return reviewDTOS;
    }
}
