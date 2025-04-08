package com.breadhardit.travelagencykata.infrastructure.persistence.repository;

import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class CustomersInMemoryRepository implements CustomersRepository {
  private final ConcurrentHashMap<String, Customer> customersById = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<String, Customer> customersByPassport = new ConcurrentHashMap<>();

  /**
   * Saves a customer to the in-memory repositories.
   *
   * <p>This method is not intended for extension.
   *
   * @param customer The customer to save. Must not be null.
   */
  @Override
  public void saveCustomer(final Customer customer) {
    customersById.put(customer.getId(), customer);
    customersByPassport.put(customer.getPassportNumber(), customer);
  }

  /**
   * Retrieves a customer from the in-memory repository by their ID.
   *
   * <p>This method is not intended for extension.
   *
   * @param id The ID of the customer to retrieve. Must not be null or empty.
   * @return An Optional containing the found customer, or an empty Optional if not found.
   */
  @Override
  public Optional<Customer> getCustomerById(final String id) {
    Customer customer = customersById.get(id);
    return customer == null ? Optional.empty() : Optional.of(customer);
  }

  /**
   * Retrieves a customer from the in-memory repository by their passport number.
   *
   * <p>This method is not intended for extension.
   *
   * @param passport The passport number of the customer to retrieve. Must not be null or empty.
   * @return An Optional containing the found customer, or an empty Optional if not found.
   */
  @Override
  public Optional<Customer> getCustomerByPassport(final String passport) {
    Customer customer = customersByPassport.get(passport);
    return customer == null ? Optional.empty() : Optional.of(customer);
  }
}
