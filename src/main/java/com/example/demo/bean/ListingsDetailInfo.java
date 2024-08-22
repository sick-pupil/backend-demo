package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName listings_detail_info
 */
@TableName(value ="listings_detail_info")
@Data
public class ListingsDetailInfo implements Serializable {
    /**
     * 
     */
    @TableField(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "listing_url")
    private String listingUrl;

    /**
     * 
     */
    @TableField(value = "scrape_id")
    private Long scrapeId;

    /**
     * 
     */
    @TableField(value = "last_scraped")
    private LocalDate lastScraped;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 
     */
    @TableField(value = "space")
    private String space;

    /**
     * 
     */
    @TableField(value = "description")
    private String description;

    /**
     * 
     */
    @TableField(value = "experiences_offered")
    private String experiencesOffered;

    /**
     * 
     */
    @TableField(value = "neighborhood_overview")
    private String neighborhoodOverview;

    /**
     * 
     */
    @TableField(value = "notes")
    private String notes;

    /**
     * 
     */
    @TableField(value = "transit")
    private String transit;

    /**
     * 
     */
    @TableField(value = "access")
    private String access;

    /**
     * 
     */
    @TableField(value = "interaction")
    private String interaction;

    /**
     * 
     */
    @TableField(value = "house_rules")
    private String houseRules;

    /**
     * 
     */
    @TableField(value = "thumbnail_url")
    private String thumbnailUrl;

    /**
     * 
     */
    @TableField(value = "medium_url")
    private String mediumUrl;

    /**
     * 
     */
    @TableField(value = "picture_url")
    private String pictureUrl;

    /**
     * 
     */
    @TableField(value = "xl_picture_url")
    private String xlPictureUrl;

    /**
     * 
     */
    @TableField(value = "host_id")
    private Long hostId;

    /**
     * 
     */
    @TableField(value = "host_url")
    private String hostUrl;

    /**
     * 
     */
    @TableField(value = "host_name")
    private String hostName;

    /**
     * 
     */
    @TableField(value = "host_since")
    private LocalDate hostSince;

    /**
     * 
     */
    @TableField(value = "host_location")
    private String hostLocation;

    /**
     * 
     */
    @TableField(value = "host_about")
    private String hostAbout;

    /**
     * 
     */
    @TableField(value = "host_response_time")
    private String hostResponseTime;

    /**
     * 
     */
    @TableField(value = "host_response_rate")
    private String hostResponseRate;

    /**
     * 
     */
    @TableField(value = "host_acceptance_rate")
    private String hostAcceptanceRate;

    /**
     * 
     */
    @TableField(value = "host_is_superhost")
    private String hostIsSuperhost;

    /**
     * 
     */
    @TableField(value = "host_thumbnail_url")
    private String hostThumbnailUrl;

    /**
     * 
     */
    @TableField(value = "host_picture_url")
    private String hostPictureUrl;

    /**
     * 
     */
    @TableField(value = "host_neighbourhood")
    private String hostNeighbourhood;

    /**
     * 
     */
    @TableField(value = "host_listings_count")
    private Long hostListingsCount;

    /**
     * 
     */
    @TableField(value = "host_total_listings_count")
    private Long hostTotalListingsCount;

    /**
     * 
     */
    @TableField(value = "host_verifications")
    private String hostVerifications;

    /**
     * 
     */
    @TableField(value = "host_has_profile_pic")
    private String hostHasProfilePic;

    /**
     * 
     */
    @TableField(value = "host_identity_verified")
    private String hostIdentityVerified;

    /**
     * 
     */
    @TableField(value = "street")
    private String street;

    /**
     * 
     */
    @TableField(value = "neighbourhood")
    private String neighbourhood;

    /**
     * 
     */
    @TableField(value = "neighbourhood_cleansed")
    private String neighbourhoodCleansed;

    /**
     * 
     */
    @TableField(value = "neighbourhood_group_cleansed")
    private String neighbourhoodGroupCleansed;

    /**
     * 
     */
    @TableField(value = "city")
    private String city;

    /**
     * 
     */
    @TableField(value = "state")
    private String state;

    /**
     * 
     */
    @TableField(value = "zipcode")
    private Long zipcode;

    /**
     * 
     */
    @TableField(value = "market")
    private String market;

    /**
     * 
     */
    @TableField(value = "smart_location")
    private String smartLocation;

    /**
     * 
     */
    @TableField(value = "country_code")
    private String countryCode;

    /**
     * 
     */
    @TableField(value = "country")
    private String country;

    /**
     * 
     */
    @TableField(value = "latitude")
    private BigDecimal latitude;

    /**
     * 
     */
    @TableField(value = "longitude")
    private BigDecimal longitude;

    /**
     * 
     */
    @TableField(value = "is_location_exact")
    private String isLocationExact;

    /**
     * 
     */
    @TableField(value = "property_type")
    private String propertyType;

    /**
     * 
     */
    @TableField(value = "room_type")
    private String roomType;

    /**
     * 
     */
    @TableField(value = "accommodates")
    private Long accommodates;

    /**
     * 
     */
    @TableField(value = "bathrooms")
    private BigDecimal bathrooms;

    /**
     * 
     */
    @TableField(value = "bedrooms")
    private Long bedrooms;

    /**
     * 
     */
    @TableField(value = "beds")
    private Long beds;

    /**
     * 
     */
    @TableField(value = "bed_type")
    private String bedType;

    /**
     * 
     */
    @TableField(value = "amenities")
    private String amenities;

    /**
     * 
     */
    @TableField(value = "square_feet")
    private Long squareFeet;

    /**
     * 
     */
    @TableField(value = "price")
    private String price;

    /**
     * 
     */
    @TableField(value = "weekly_price")
    private String weeklyPrice;

    /**
     * 
     */
    @TableField(value = "monthly_price")
    private String monthlyPrice;

    /**
     * 
     */
    @TableField(value = "security_deposit")
    private String securityDeposit;

    /**
     * 
     */
    @TableField(value = "cleaning_fee")
    private String cleaningFee;

    /**
     * 
     */
    @TableField(value = "guests_included")
    private Long guestsIncluded;

    /**
     * 
     */
    @TableField(value = "extra_people")
    private String extraPeople;

    /**
     * 
     */
    @TableField(value = "minimum_nights")
    private Long minimumNights;

    /**
     * 
     */
    @TableField(value = "maximum_nights")
    private Long maximumNights;

    /**
     * 
     */
    @TableField(value = "calendar_updated")
    private String calendarUpdated;

    /**
     * 
     */
    @TableField(value = "has_availability")
    private String hasAvailability;

    /**
     * 
     */
    @TableField(value = "availability_30")
    private Long availability30;

    /**
     * 
     */
    @TableField(value = "availability_60")
    private Long availability60;

    /**
     * 
     */
    @TableField(value = "availability_90")
    private Long availability90;

    /**
     * 
     */
    @TableField(value = "availability_365")
    private Long availability365;

    /**
     * 
     */
    @TableField(value = "calendar_last_scraped")
    private LocalDate calendarLastScraped;

    /**
     * 
     */
    @TableField(value = "number_of_reviews")
    private Long numberOfReviews;

    /**
     * 
     */
    @TableField(value = "first_review")
    private LocalDate firstReview;

    /**
     * 
     */
    @TableField(value = "last_review")
    private LocalDate lastReview;

    /**
     * 
     */
    @TableField(value = "review_scores_rating")
    private Long reviewScoresRating;

    /**
     * 
     */
    @TableField(value = "review_scores_accuracy")
    private Long reviewScoresAccuracy;

    /**
     * 
     */
    @TableField(value = "review_scores_cleanliness")
    private Long reviewScoresCleanliness;

    /**
     * 
     */
    @TableField(value = "review_scores_checkin")
    private Long reviewScoresCheckin;

    /**
     * 
     */
    @TableField(value = "review_scores_communication")
    private Long reviewScoresCommunication;

    /**
     * 
     */
    @TableField(value = "review_scores_location")
    private Long reviewScoresLocation;

    /**
     * 
     */
    @TableField(value = "review_scores_value")
    private Long reviewScoresValue;

    /**
     * 
     */
    @TableField(value = "requires_license")
    private String requiresLicense;

    /**
     * 
     */
    @TableField(value = "license")
    private String license;

    /**
     * 
     */
    @TableField(value = "jurisdiction_names")
    private String jurisdictionNames;

    /**
     * 
     */
    @TableField(value = "instant_bookable")
    private String instantBookable;

    /**
     * 
     */
    @TableField(value = "cancellation_policy")
    private String cancellationPolicy;

    /**
     * 
     */
    @TableField(value = "require_guest_profile_picture")
    private String requireGuestProfilePicture;

    /**
     * 
     */
    @TableField(value = "require_guest_phone_verification")
    private String requireGuestPhoneVerification;

    /**
     * 
     */
    @TableField(value = "calculated_host_listings_count")
    private Long calculatedHostListingsCount;

    /**
     * 
     */
    @TableField(value = "reviews_per_month")
    private BigDecimal reviewsPerMonth;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}