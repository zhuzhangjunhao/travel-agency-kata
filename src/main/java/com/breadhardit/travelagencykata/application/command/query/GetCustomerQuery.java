package com.breadhardit.travelagencykata.application.command.query;

import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.util.StringUtils;

@Value
@Builder
public final class GetCustomerQuery {
  @Builder.Default @Getter @Setter private String passport = "";
  @Builder.Default @Getter @Setter private String id = "";
  @Getter @Setter private CustomersRepository customersRepository;

  public Optional<Customer> handle() {
    if (!StringUtils.hasText(passport)) {
      return customersRepository.getCustomerById(id);
    }
    if (!StringUtils.hasText(id)) {
      return customersRepository.getCustomerByPassport(id);
    }
    return Optional.empty();
  }
}
