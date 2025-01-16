package dev.arias.huapaya.repair_shop.persistence.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saleDetails")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal discount;

    private BigDecimal totalAmount;

    private Boolean porcentageDiscount;

    private Boolean status;

    @PrePersist
    private void PrePersist() {
        this.status = true;
    }

}
