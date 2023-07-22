package com.info.javajediprimerapp.service.review;

import com.info.javajediprimerapp.domain.Review;
import com.info.javajediprimerapp.model.dto.review.ReviewDTO;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewDTO reviewDTO);

    List<Review> createAListOfReviews(List<ReviewDTO> reviewDTOS);
}
