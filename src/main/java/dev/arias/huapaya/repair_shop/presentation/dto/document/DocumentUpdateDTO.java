package dev.arias.huapaya.repair_shop.presentation.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentUpdateDTO {

    private String name;

    private String abbreviation;

}
