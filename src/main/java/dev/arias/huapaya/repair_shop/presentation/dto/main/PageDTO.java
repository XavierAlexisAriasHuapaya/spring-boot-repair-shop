package dev.arias.huapaya.repair_shop.presentation.dto.main;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDTO<T> {

    private List<T> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElements;

}