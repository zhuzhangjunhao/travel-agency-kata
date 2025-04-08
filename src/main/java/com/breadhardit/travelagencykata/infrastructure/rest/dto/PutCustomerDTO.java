package com.breadhardit.travelagencykata.infrastructure.rest.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
public class PutCustomerDTO {
  @Getter @Setter private String name;
  @Getter @Setter private String surnames;
  @Getter @Setter private LocalDate birthDate;
  @Getter @Setter private String passportNumber;
  @Getter @Setter private Boolean active;
}
