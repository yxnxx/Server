package com.locat.api.infrastructure.repository.user;

import com.locat.api.domain.user.entity.UserTermsAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermsAgreementRepository extends JpaRepository<UserTermsAgreement, Long> {}
