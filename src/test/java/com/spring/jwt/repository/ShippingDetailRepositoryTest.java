package com.spring.jwt.repository;

import com.spring.jwt.entity.ShippingDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShippingDetailRepositoryTest {

    @Mock
    private ShippingDetailRepository shippingDetailRepository;

    @InjectMocks
    private ShippingDetail shippingDetail;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        ShippingDetail detail1 = new ShippingDetail();
        detail1.setShippingAddress("Address 1");

        ShippingDetail detail2 = new ShippingDetail();
        detail2.setShippingAddress("Address 2");

        List<ShippingDetail> mockShippingDetails = Arrays.asList(detail1, detail2);

        when(shippingDetailRepository.findAll()).thenReturn(mockShippingDetails);

        List<ShippingDetail> shippingDetails = shippingDetailRepository.findAll();

        assertEquals(2, shippingDetails.size());
        assertEquals(mockShippingDetails, shippingDetails);
    }

    @Test
    public void testFindById() {
        ShippingDetail detail = new ShippingDetail();
        detail.setShippingAddress("Address 1");

        when(shippingDetailRepository.findById(1)).thenReturn(Optional.of(detail));

        Optional<ShippingDetail> foundDetail = shippingDetailRepository.findById(1);

        assertTrue(foundDetail.isPresent());
        assertEquals(detail, foundDetail.get());
    }

    @Test
    public void testSave() {
        ShippingDetail detail = new ShippingDetail();
        detail.setShippingAddress("Address 1");

        when(shippingDetailRepository.save(detail)).thenReturn(detail);

        ShippingDetail savedDetail = shippingDetailRepository.save(detail);

        assertEquals(detail, savedDetail);
        verify(shippingDetailRepository, times(1)).save(detail);
    }

    @Test
    public void testDelete() {
        ShippingDetail detail = new ShippingDetail();
        detail.setShippingAddress("Address 1");

        doNothing().when(shippingDetailRepository).delete(detail);

        shippingDetailRepository.delete(detail);
        verify(shippingDetailRepository, times(1)).delete(detail);
    }
}
