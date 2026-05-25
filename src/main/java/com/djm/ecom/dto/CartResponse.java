package com.djm.ecom.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private long cardId;
    private List<String> itemList;
}
