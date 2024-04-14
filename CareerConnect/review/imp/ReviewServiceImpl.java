package com.CareerConnect.CareerConnect.review.imp;

import com.CareerConnect.CareerConnect.company.Company;
import com.CareerConnect.CareerConnect.company.CompanyService;
import com.CareerConnect.CareerConnect.review.Review;
import com.CareerConnect.CareerConnect.review.ReviewRepository;
import com.CareerConnect.CareerConnect.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService= companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }
}
