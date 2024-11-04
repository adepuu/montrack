package com.adepuu.montrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "wallet")
public class Wallet {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_id_gen")
  @SequenceGenerator(name = "wallet_id_gen", sequenceName = "wallet_wallet_id_seq", allocationSize = 1)
  @Column(name = "wallet_id", nullable = false)
  private Long id;

  @NotNull
  @ColumnDefault("false")
  @Column(name = "is_active", nullable = false)
  private Boolean isActive = false;

  @Size(max = 100)
  @NotNull
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "current_expense", nullable = false, precision = 10, scale = 5)
  private BigDecimal currentExpense;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "current_income", nullable = false, precision = 10, scale = 5)
  private BigDecimal currentIncome;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "allocated_budget", nullable = false, precision = 10, scale = 5)
  private BigDecimal allocatedBudget;

  @NotNull
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @NotNull
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name = "deleted_at")
  private OffsetDateTime deletedAt;

}