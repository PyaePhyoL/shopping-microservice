package com.jdc.balance.shoppingui.service;

import com.jdc.balance.shoppingui.dto.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {
    private final RestClient backendRestClient;
    private final RestClient paymentRestClient;

    public ProductService() {
        backendRestClient = RestClient.builder().baseUrl("http://localhost:8090/shopping/backend").build();
        paymentRestClient = RestClient.builder().baseUrl("http://localhost:8070/account").build();
    }


    public List<ProductDto> getAllProducts() {
        return backendRestClient.get().uri("/products")
                .retrieve().body(new ParameterizedTypeReference<List<ProductDto>>() {});
    }

    public List<ProductDto> getProductByCategoryId(int id) {
        return backendRestClient.get().uri("/products/" + id)
                .retrieve().body(new ParameterizedTypeReference<List<ProductDto>>() {});
    }

    public void makeBuyTransaction(UserInfoDto userInfo, double amount) {
        RequestDataDto requestDataDto = new RequestDataDto();
        requestDataDto.setCreditNumber(userInfo.getCreditCardNumber());
        requestDataDto.setName(userInfo.getName());
        requestDataDto.setAmount(amount + 3);

        paymentRestClient.post()
                .uri("/buy-transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDataDto)
                .retrieve()
                .body(String.class);
    }
}
