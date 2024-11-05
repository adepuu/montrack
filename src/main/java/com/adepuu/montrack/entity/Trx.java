package com.adepuu.montrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Filter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "trx")
@Filter(name = "not deleted only", condition = "deleted_at is null")
public class Trx {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trx_id_gen")
  @SequenceGenerator(name = "trx_id_gen", sequenceName = "trx_trx_id_seq", allocationSize = 1)
  @Column(name = "trx_id", nullable = false)
  private Long id;

  @NotNull
  @Column(name = "value", nullable = false, precision = 10, scale = 5)
  private BigDecimal value;

  @NotNull
  @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
  private String description;

  @Size(max = 200)
  @Column(name = "attachment_url", length = 200)
  private String attachmentUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pocket_id")
  private Pocket pocket;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id")
  private Wallet wallet;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "goal_id")
  private Goal goal;

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

  @PrePersist
  protected void onCreate() {
    createdAt = OffsetDateTime.now();
    updatedAt = OffsetDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = OffsetDateTime.now();
  }

  @PreRemove
  protected void onRemove() {
    deletedAt = OffsetDateTime.now();
  }
}