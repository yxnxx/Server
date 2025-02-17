package com.locat.api.domain.auth.template;

import com.locat.api.domain.auth.entity.OAuth2ProviderToken;
import com.locat.api.global.exception.ApiExceptionType;
import com.locat.api.global.exception.NoSuchEntityException;
import com.locat.api.infrastructure.redis.OAuth2ProviderTokenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public abstract class AbstractOAuth2Template implements OAuth2Template {

  protected static final Logger log = LoggerFactory.getLogger(AbstractOAuth2Template.class);

  protected final OAuth2Properties oAuth2Properties;
  protected final OAuth2ProviderTokenRepository providerTokenRepository;

  protected OAuth2ProviderToken fetchTokenByAccessToken(String accessToken) {
    return this.providerTokenRepository
        .findByAccessToken(accessToken)
        .orElseThrow(() -> new NoSuchEntityException(ApiExceptionType.NOT_FOUND_AUTH));
  }

  protected OAuth2ProviderToken fetchToken(String userOAuthId) {
    return this.providerTokenRepository
        .findById(userOAuthId)
        .orElseThrow(() -> new NoSuchEntityException(ApiExceptionType.NOT_FOUND_AUTH));
  }
}
