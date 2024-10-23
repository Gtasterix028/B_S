package com.spring.jwt.service;

import com.spring.jwt.repository.ShippingDetailRepository;
import com.spring.jwt.Interfaces.ITax;
import com.spring.jwt.dto.ShippingDto;
import com.spring.jwt.entity.ShippingDetail;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingService implements ITax {

    @Autowired
    private ModelMapper mapper;


    @Autowired
    private ShippingDetailRepository shippingDetailRepository;

    @Override
    public ShippingDto SaveShipping(ShippingDto shippingDto) {

        ShippingDetail shippingDetail = mapper.map(shippingDto, ShippingDetail.class);
        ShippingDetail saveshipping = shippingDetailRepository.save(shippingDetail);
        ShippingDto toDTO = mapper.map(saveshipping, ShippingDto.class);
        return toDTO;
    }

    @Override
    public ShippingDto updateShipping(int id, ShippingDto shippingDto) {
        Optional<ShippingDetail> shippingDetailOptional = shippingDetailRepository.findById(id);
        if (shippingDetailOptional.isPresent()) {
            ShippingDetail shippingDetail = shippingDetailOptional.get();


            //shippingDetail.setInvoicedId(shippingDto.getInvoicedId());
            shippingDetail.setShippingAddress(shippingDto.getShippingAddress());
            shippingDetail.setShippingDate(shippingDto.getShippingDate());
            shippingDetail.setEstimatedArrivalDate(shippingDto.getEstimatedArrivalDAte());

            ShippingDetail updatedShipping = shippingDetailRepository.save(shippingDetail);
            return mapper.map(updatedShipping, ShippingDto.class);
        } else {
            throw new RuntimeException("Shipping detail not found for ID: " + id);
        }
    }

    @Override
    public ShippingDto getById(int id) {
        Optional<ShippingDetail> shippingDetailOptional = shippingDetailRepository.findById(id);
        if (shippingDetailOptional.isPresent()) {
            return mapper.map(shippingDetailOptional.get(), ShippingDto.class);
        } else {
            throw new RuntimeException("Shipping detail not found for ID: " + id);
        }
    }

    @Override
    public List<ShippingDto> getAll() {
        List<ShippingDetail> shippingDetails = shippingDetailRepository.findAll();
        return shippingDetails.stream()
                .map(shippingDetail -> mapper.map(shippingDetail, ShippingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(int id) {
        Optional<ShippingDetail> shippingDetailOptional = shippingDetailRepository.findById(id);
        if (shippingDetailOptional.isPresent()) {
            shippingDetailRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Shipping detail not found for ID: " + id);
        }
    }
}




