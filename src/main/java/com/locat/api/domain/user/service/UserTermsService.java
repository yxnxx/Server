package com.locat.api.domain.user.service;

import com.locat.api.domain.user.dto.UserRegisterDto;
import com.locat.api.domain.user.entity.User;

public interface UserTermsService {

  void register(User user, UserRegisterDto registerDto);
}
