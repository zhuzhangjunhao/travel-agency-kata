package com.breadhardit.travelagencykata.infrastructure.rest;

import com.breadhardit.travelagencykata.application.command.command.CreateCustomerCommand;
import com.breadhardit.travelagencykata.application.command.query.GetCustomerQuery;
import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import com.breadhardit.travelagencykata.infrastructure.rest.dto.GetCustomerDTO;
import com.breadhardit.travelagencykata.infrastructure.rest.dto.PutCustomerDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CustomersController {

  private final CustomersRepository customersRepository;

  /**
   * Creates a new customer.
   *
   * <p>This method is not intended for extension.
   *
   * @param customer The data for the new customer. Must not be null.
   * @return A ResponseEntity with the URI of the newly created customer.
   */
  @PutMapping("/customers")
  @Transactional
  public ResponseEntity putCustomer(@RequestBody final PutCustomerDTO customer) {
    log.info("POST customer {}", customer);
    CreateCustomerCommand command =
        CreateCustomerCommand.builder()
            .name(customer.getName())
            .surnames(customer.getSurnames())
            .birthDate(customer.getBirthDate())
            .passportNumber(customer.getPassportNumber())
            .customersRepository(customersRepository)
            .build();
    String id = command.handle();
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{customer-id}")
                .buildAndExpand(id)
                .toUri())
        .build();
  }

  /**
   * Retrieves a customer by their ID.
   *
   * <p>This method is not intended for extension.
   *
   * @param customerId The ID of the customer to retrieve. Must not be null or empty.
   * @return A ResponseEntity containing the customer's information, or no content if not found.
   */
  @GetMapping("/customers/{customer-id}")
  public ResponseEntity getCustomer(@PathVariable final String customerId) {
    log.info("Getting the customer {}", customerId);
    Optional<Customer> customer =
        GetCustomerQuery.builder()
            .customersRepository(customersRepository)
            .id(customerId)
            .build()
            .handle();
    return customer.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(
            GetCustomerDTO.builder()
                .name(customer.get().getName())
                .surnames(customer.get().getSurnames())
                .birthDate(customer.get().getBirthDate())
                .passportNumber(customer.get().getPassportNumber())
                .build());
  }

  /**
   * Retrieves a customer by their passport number.
   *
   * <p>This method is not intended for extension.
   *
   * @param passportNumber The passport number of the customer to retrieve. Must not be null or
   *     empty.
   * @return A ResponseEntity containing a list with the customer's information, or no content if
   *     not found.
   */
  @GetMapping("/customers")
  public ResponseEntity getCustomers(
      @RequestParam(name = "passport-number") final String passportNumber) {
    log.info("Getting the customer with the passport {}", passportNumber);
    Optional<Customer> customer =
        GetCustomerQuery.builder()
            .customersRepository(customersRepository)
            .passport(passportNumber)
            .build()
            .handle();
    return customer.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(
            List.of(
                GetCustomerDTO.builder()
                    .name(customer.get().getName())
                    .surnames(customer.get().getSurnames())
                    .birthDate(customer.get().getBirthDate())
                    .passportNumber(customer.get().getPassportNumber())
                    .build()));
  }
}
