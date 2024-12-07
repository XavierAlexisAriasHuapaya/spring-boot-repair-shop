package dev.arias.huapaya.repair_shop.presentation.dto.document;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentFindOneDTO {

    private Long id;

    private String name;

    private String abbreviation;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean status;

}
