package com.sparta.deliveryapi.service;

import com.sparta.deliveryapi.dto.RestaurantRequestDto;
import com.sparta.deliveryapi.model.Restaurant;
import com.sparta.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;


    // 음식점 등록
    @SneakyThrows
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언
    public Restaurant createRestaurant(RestaurantRequestDto requestDto) {
        int oderAbleValue = requestDto.getMinOrderPrice();
        int deliveryAbleValue = requestDto.getDeliveryFee();
        if (oderAbleValue < 1000 || oderAbleValue > 100000) throw new IllegalAccessException("최소주문가격 확인");
        if (oderAbleValue % 100 != 0 ) throw  new IllegalAccessException("최소주문가격단위 확인");
        if (deliveryAbleValue > 10000) throw new  IllegalAccessException("기본 배달비 확인");
        if (deliveryAbleValue % 500 != 0) throw new IllegalAccessException("기본 배달비단위 확인");
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}


