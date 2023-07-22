package com.info.javajediprimerapp.mapper.review.impl;

import com.info.javajediprimerapp.domain.Review;
import com.info.javajediprimerapp.mapper.review.ReviewMapper;
import com.info.javajediprimerapp.model.dto.review.ReviewDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review reviewDTOToReview(ReviewDTO reviewDTO) {
        return Review.builder()
                .uuid(UUID.randomUUID())
                .title(reviewDTO.getTitle())
                .content(reviewDTO.getContent())
                .calification(reviewDTO.getCalification())
                .dateOfCreation(LocalDateTime.now())
                .build();
    }

    @Override
    public ReviewDTO reviewToReviewDTO(Review review) {
        return ReviewDTO.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .calification(review.getCalification())
                .build();
    }
}
