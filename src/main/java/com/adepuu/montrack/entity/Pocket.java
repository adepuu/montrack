package com.adepuu.montrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pocket")
public class Pocket {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pocket_id_gen")
  @SequenceGenerator(name = "pocket_id_gen", sequenceName = "pocket_pocket_id_seq", allocationSize = 1)
  @Column(name = "pocket_id", nullable = false)
  private Long id;

  @Size(max = 100)
  @NotNull
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Size(max = 50)
  @NotNull
  @Column(name = "emoji_code", nullable = false, length = 50)
  private String emojiCode;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "allocated_budget", nullable = false, precision = 10, scale = 5)
  private BigDecimal allocatedBudget;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "used_amount", nullable = false, precision = 10, scale = 5)
  private BigDecimal usedAmount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id")
  private Wallet wallet;

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

  @OneToMany(mappedBy = "pocket")
  private Set<Trx> trxes = new LinkedHashSet<>();

  @Transient
  private String someIrrelevantProperties;
}