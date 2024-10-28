package com.spring.jwt.service;

import com.spring.jwt.dto.ShippingDto;
import com.spring.jwt.entity.ShippingDetail;
import com.spring.jwt.repository.ShippingDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShippingServiceTest {

    @Mock
    private ShippingDetailRepository shippingDetailRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ShippingService shippingService;

    private ShippingDto shippingDto;
    private ShippingDetail shippingDetail;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        shippingDto = new ShippingDto();
        shippingDto.setShippingAddress("123 Main St");
        shippingDto.setShippingDate(LocalDate.now());
        shippingDto.setEstimatedArrivalDAte(LocalDate.now().plusDays(5));

        shippingDetail = new ShippingDetail();
        shippingDetail.setShippingAddress("123 Main St");
        shippingDetail.setShippingDate(LocalDate.now());
        shippingDetail.setEstimatedArrivalDate(LocalDate.now().plusDays(5));
    }

    @Test
    public void testSaveShipping() {
        when(mapper.map(shippingDto, ShippingDetail.class)).thenReturn(shippingDetail);
        when(shippingDetailRepository.save(shippingDetail)).thenReturn(shippingDetail);
        when(mapper.map(shippingDetail, ShippingDto.class)).thenReturn(shippingDto);

        ShippingDto savedShipping = shippingService.SaveShipping(shippingDto);

        assertNotNull(savedShipping);
        assertEquals(shippingDto.getShippingAddress(), savedShipping.getShippingAddress());
        verify(shippingDetailRepository, times(1)).save(shippingDetail);
    }

    @Test
    public void testUpdateShipping() {
        int id = 1;
        when(shippingDetailRepository.findById(id)).thenReturn(Optional.of(shippingDetail));
        when(shippingDetailRepository.save(shippingDetail)).thenReturn(shippingDetail);
        when(mapper.map(shippingDetail, ShippingDto.class)).thenReturn(shippingDto);

        ShippingDto updatedShipping = shippingService.updateShipping(id, shippingDto);

        assertNotNull(updatedShipping);
        assertEquals(shippingDto.getShippingAddress(), updatedShipping.getShippingAddress());
        verify(shippingDetailRepository, times(1)).save(shippingDetail);
    }

    @Test
    public void testGetById() {
        int id = 1;
        when(shippingDetailRepository.findById(id)).thenReturn(Optional.of(shippingDetail));
        when(mapper.map(shippingDetail, ShippingDto.class)).thenReturn(shippingDto);

        ShippingDto foundShipping = shippingService.getById(id);

        assertNotNull(foundShipping);
        assertEquals(shippingDto.getShippingAddress(), foundShipping.getShippingAddress());
        verify(shippingDetailRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAll() {
        List<ShippingDetail> shippingDetails = Arrays.asList(shippingDetail);
        when(shippingDetailRepository.findAll()).thenReturn(shippingDetails);
        when(mapper.map(shippingDetail, ShippingDto.class)).thenReturn(shippingDto);

        List<ShippingDto> shippingDtoList = shippingService.getAll();

        assertEquals(1, shippingDtoList.size());
        assertEquals(shippingDto.getShippingAddress(), shippingDtoList.get(0).getShippingAddress());
        verify(shippingDetailRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        int id = 1;
        when(shippingDetailRepository.findById(id)).thenReturn(Optional.of(shippingDetail));

        boolean result = shippingService.deleteById(id);

        assertTrue(result);
        verify(shippingDetailRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteById_NotFound() {
        int id = 1;
        when(shippingDetailRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            shippingService.deleteById(id);
        });

        assertEquals("Shipping detail not found for ID: " + id, exception.getMessage());
    }
}
