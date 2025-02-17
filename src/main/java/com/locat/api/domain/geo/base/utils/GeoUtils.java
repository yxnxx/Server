package com.locat.api.domain.geo.base.utils;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

public final class GeoUtils {

  /** 지구의 반지름 (단위: km) */
  private static final double EARTH_RADIUS = 6371.0;

  private GeoUtils() {
    // Utility class
  }

  /**
   * 두 지리 좌표 간의 거리를 계산하여 주어진 반경 내에 있는지 확인합니다.
   *
   * @param centralPoint 반경의 중심이 되는 기준 좌표 (중심점)
   * @param targetPoint 반경 내에 있는지 확인할 대상 좌표
   * @param radius 중심점을 기준으로 하는 반경 거리
   * @return 대상 좌표가 반경 내에 있으면 {@code true}, 그렇지 않으면 {@code false}를 반환
   */
  public static boolean isInRadius(
      final Point centralPoint, final Point targetPoint, final Distance radius) {
    return calculateDistanceBetween(centralPoint, targetPoint) <= radius.getValue();
  }

  /**
   * 두 좌표 간의 거리를 하버사인 공식을 이용하여 계산합니다.
   *
   * @param p1 첫 번째 좌표
   * @param p2 두 번째 좌표
   * @return 두 좌표 간의 거리
   */
  public static double calculateDistanceBetween(final Point p1, final Point p2) {
    final double lat1 = Math.toRadians(p1.getX());
    final double lon1 = Math.toRadians(p1.getY());
    final double lat2 = Math.toRadians(p2.getX());
    final double lon2 = Math.toRadians(p2.getY());

    final double deltaLat = lat2 - lat1;
    final double deltaLon = lon2 - lon1;

    final double a =
        Math.pow(Math.sin(deltaLat / 2), 2)
            + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(deltaLon / 2), 2);
    final double c = 2 * Math.asin(Math.sqrt(a));

    return EARTH_RADIUS * c;
  }

  /**
   * 미터 단위의 거리를 킬로미터 단위로 변환합니다.
   *
   * @param meter 미터 단위의 거리
   * @return 킬로미터 단위의 거리
   */
  public static double toKilometer(final double meter) {
    return meter / 1000;
  }

  /**
   * 킬로미터 단위의 거리를 미터 단위로 변환합니다.
   *
   * @param kilometer 킬로미터 단위의 거리
   * @return 미터 단위의 거리
   */
  public static double toMeter(final double kilometer) {
    return kilometer * 1000;
  }
}
