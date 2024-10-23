package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.ShippingDto;

import java.util.List;

public interface ITax {

    public ShippingDto SaveShipping(ShippingDto shippingDto);

    ShippingDto updateShipping(int id, ShippingDto shippingDto);

    ShippingDto getById(int id);

    List<ShippingDto> getAll();

    boolean deleteById(int id);


}


