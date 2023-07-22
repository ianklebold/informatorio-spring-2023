package com.info.javajediprimerapp.mapper.review;

import com.info.javajediprimerapp.domain.Review;
import com.info.javajediprimerapp.model.dto.review.ReviewDTO;

public interface ReviewMapper {
    Review reviewDTOToReview(ReviewDTO review);
    ReviewDTO reviewToReviewDTO(Review reviewDTO);
}
