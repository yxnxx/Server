package com.locat.api.domain.geo.found.dto;

import com.locat.api.domain.geo.base.dto.GeoItemSearchCriteria;
import com.locat.api.domain.geo.base.dto.GeoItemSortType;
import com.locat.api.domain.geo.base.utils.GeoUtils;
import lombok.Builder;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

/**
 * 습득물 검색 조건 DTO
 *
 * @param onlyMine 내가 등록한 습득물만 검색 여부
 * @param location 검색 위치가 될 중심 좌표
 * @param distance 검색 반경
 * @param sort 정렬 기준
 */
@Builder
public record FoundItemSearchDto(
    Boolean onlyMine, Point location, Distance distance, GeoItemSortType sort)
    implements GeoItemSearchCriteria {

  @Override
  public Boolean getOnlyMine() {
    return this.onlyMine;
  }

  @Override
  public Point getLocation() {
    return this.location;
  }

  @Override
  public Distance getDistance() {
    return this.distance;
  }

  @Override
  public GeoItemSortType getSort() {
    return this.sort;
  }

  public static FoundItemSearchDto fromRequest(
      Boolean onlyMine, Point location, Double radius, String sort) {
    return FoundItemSearchDto.builder()
        .onlyMine(onlyMine)
        .location(location)
        .distance(new Distance(GeoUtils.toKilometer(radius), Metrics.KILOMETERS))
        .sort(GeoItemSortType.toEnum(sort))
        .build();
  }
}
