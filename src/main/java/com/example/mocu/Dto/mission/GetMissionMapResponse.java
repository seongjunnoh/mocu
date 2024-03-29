package com.example.mocu.Dto.mission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMissionMapResponse {
    // user가 모은 미션스탬프 개수, 보상, 미션맵 생성시각, 미션맵 완료 여부
    private int numOfStamp;
    private String reward;
    private String createdDate;
    private String status;
}
