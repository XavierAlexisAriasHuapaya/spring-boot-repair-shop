package dev.arias.huapaya.repair_shop.persistence.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "masters")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "master", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<MasterDetailEntity> master_details;

    private boolean status;

    @PrePersist
    private void prePersist() {
        this.status = true;
    }

}
