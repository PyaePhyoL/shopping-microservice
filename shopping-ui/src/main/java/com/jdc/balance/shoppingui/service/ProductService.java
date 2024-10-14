package com.jdc.balance.shoppingui.service;

import com.jdc.balance.shoppingui.dto.ProductDto;
import com.jdc.balance.shoppingui.dto.RequestDataDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ProductService {
    private RestClient restClient;

    public ProductService() {
        restClient = RestClient.builder().baseUrl("http://localhost:8090/shopping/backend").build();
    }

    public List<ProductDto> getAllProducts() {
        return restClient.get().uri("/products")
                .retrieve().body(new ParameterizedTypeReference<List<ProductDto>>() {});
    }

    public List<ProductDto> getProductByCategoryId(int id) {
        return restClient.get().uri("/products/" + id)
                .retrieve().body(new ParameterizedTypeReference<List<ProductDto>>() {});
    }

    public void makeBuyTransaction(RequestDataDto requestDataDto) {
        restClient = RestClient.builder().baseUrl("http://localhost:8070/account").build();

        restClient.post().uri("/buy-transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDataDto)
                .retrieve()
                .body(String.class);
    }
}
