package com.example.mocu.Dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchAvailableReviewRequest {
    private long userId;
    private long storeId;
    private int rate;
    private String content;
}
