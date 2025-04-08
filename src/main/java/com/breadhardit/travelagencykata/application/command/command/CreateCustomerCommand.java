package com.breadhardit.travelagencykata.application.command.command;

import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.Value;

@Value
@Builder
public final class CreateCustomerCommand {
  @Getter @Setter private String id = UUID.randomUUID().toString();
  @Getter @Setter private String name;
  @Getter @Setter private String surnames;
  @Getter @Setter private LocalDate birthDate;
  @Getter @Setter private String passportNumber;
  @Getter @Setter private CustomersRepository customersRepository;

  @SneakyThrows
  public String handle() {
    Customer customer =
        Customer.builder()
            .id(id)
            .name(name)
            .surnames(surnames)
            .birthDate(birthDate)
            .passportNumber(passportNumber)
            .build();
    customersRepository.saveCustomer(customer);
    return customer.getId();
  }
}
