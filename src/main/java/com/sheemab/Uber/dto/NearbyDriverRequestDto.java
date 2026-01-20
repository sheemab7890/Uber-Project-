package com.sheemab.Uber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearbyDriverRequestDto {
    private Double latitude;
    private Double longitude;
    private Double radius;
}
