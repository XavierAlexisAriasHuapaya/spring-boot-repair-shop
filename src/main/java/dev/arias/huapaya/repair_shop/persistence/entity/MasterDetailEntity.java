package dev.arias.huapaya.repair_shop.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "masterDetails")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MasterDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy para mejorar rendimiento en paginación
    @JoinColumn(name = "master_id", nullable = false) // Agrega la FK correctamente
    private MasterEntity master;

    private String description;

    private String value;

}
