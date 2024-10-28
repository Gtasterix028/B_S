package com.spring.jwt.repository;

import com.spring.jwt.entity.Discount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DiscountRepositoryTest {

    @Mock
    private DiscountRepository discountRepository;

    @InjectMocks
    private Discount discount;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Discount discount1 = new Discount();
        discount1.setDiscountId(1);
        discount1.setDiscountName("SUMMER2024");
        discount1.setDiscountValue(10.0);

        Discount discount2 = new Discount();
        discount2.setDiscountId(2);
        discount2.setDiscountName("WINTER2024");
        discount2.setDiscountValue(20.0);

        List<Discount> mockDiscounts = Arrays.asList(discount1, discount2);

        when(discountRepository.findAll()).thenReturn(mockDiscounts);

        List<Discount> discounts = discountRepository.findAll();

        assertEquals(2, discounts.size());
        assertEquals(mockDiscounts, discounts);
    }

    @Test
    public void testFindById() {
        Discount discount = new Discount();
        discount.setDiscountId(1);
        discount.setDiscountName("SUMMER2024");
        discount.setDiscountValue(10.0);

        when(discountRepository.findById(1)).thenReturn(Optional.of(discount));

        Optional<Discount> foundDiscount = discountRepository.findById(1);

        assertEquals(true, foundDiscount.isPresent());
        assertEquals(discount, foundDiscount.get());
    }

    @Test
    public void testSave() {
        Discount discount = new Discount();
        discount.setDiscountName("SUMMER2024");
        discount.setDiscountValue(10.0);

        when(discountRepository.save(discount)).thenReturn(discount);

        Discount savedDiscount = discountRepository.save(discount);

        assertEquals(discount, savedDiscount);
        verify(discountRepository, times(1)).save(discount);
    }

    @Test
    public void testDelete() {
        Discount discount = new Discount();
        discount.setDiscountId(1);
        discount.setDiscountName("SUMMER2024");
        discount.setDiscountValue(10.0);

        doNothing().when(discountRepository).delete(discount);

        discountRepository.delete(discount);
        verify(discountRepository, times(1)).delete(discount);
    }
}
