package com.breadhardit.travelagencykata.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CUSTOMERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // Added explicit NoArgsConstructor
public class CustomerEntity {
  @Id
  @Column(name = "ID")
  @Getter
  @Setter
  private String id;

  @Column(name = "NAME")
  @Getter
  @Setter
  private String name;

  @Column(name = "SURNAMES")
  @Getter
  @Setter
  private String surnames;

  @Column(name = "BIRTH_DATE")
  @Getter
  @Setter
  private LocalDate birthDate;

  @Column(name = "PASSPORT_NUMBER")
  @Getter
  @Setter
  private String passportNumber;

  @Column(name = "ENROLLMENT_DATE")
  @Getter
  @Setter
  private LocalDate enrollmentDate;

  @Column(name = "ACTIVE")
  @Getter
  @Setter
  private Boolean active;
}
