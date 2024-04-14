package com.CareerConnect.CareerConnect.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies/{companyId}")  // Corrected path variable syntax
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {  // Corrected path variable name
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if (isReviewSaved)
          return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Saved", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId ){
        return new  ResponseEntity<>(reviewService.getReview(companyId,reviewId), HttpStatus.OK);
    }



/*
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Optional<Review> review = Optional.ofNullable(reviewService.getReview(companyId, reviewId));
        return review.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

 */

}
