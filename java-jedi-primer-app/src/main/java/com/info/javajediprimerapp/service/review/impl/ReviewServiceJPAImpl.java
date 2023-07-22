package com.info.javajediprimerapp.service.review.impl;

import com.info.javajediprimerapp.domain.Review;
import com.info.javajediprimerapp.mapper.review.ReviewMapper;
import com.info.javajediprimerapp.model.dto.review.ReviewDTO;
import com.info.javajediprimerapp.repository.reviews.ReviewRepository;
import com.info.javajediprimerapp.service.review.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceJPAImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;

    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.reviewDTOToReview(reviewDTO);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> createAllOfReviews(List<ReviewDTO> reviewDTOS) {
        List<Review> reviews = new ArrayList<>();

        for (ReviewDTO reviewDto: reviewDTOS) {
            reviews.add(createReview(reviewDto));
        }

        return reviews;
    }
}
