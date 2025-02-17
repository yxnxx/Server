package com.locat.api.domain.geo.base.validation;

import com.locat.api.domain.geo.base.annotation.FoundItemValidation;
import com.locat.api.domain.geo.found.dto.request.FoundItemRegisterRequest;

public class FoundItemRegisterRequestValidator
    extends GeoItemRegisterRequestValidator<FoundItemValidation, FoundItemRegisterRequest> {

  @Override
  protected boolean validateCoordinates(FoundItemRegisterRequest request) {
    return LATITUDE_LONGITUDE_PATTERN.matcher(request.lat().toString()).matches()
        && LATITUDE_LONGITUDE_PATTERN.matcher(request.lng().toString()).matches();
  }

  @Override
  protected boolean validateColorCodesExist(FoundItemRegisterRequest request) {
    return !request.colorIds().isEmpty();
  }

  @Override
  protected boolean validateColorCodesLimit(FoundItemRegisterRequest request) {
    return request.colorIds().size() <= MAX_COLOR_CODES_SIZE;
  }
}
