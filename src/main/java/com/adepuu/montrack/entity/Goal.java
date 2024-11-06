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
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "goal")
public class Goal {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goal_id_gen")
  @SequenceGenerator(name = "goal_id_gen", sequenceName = "goal_goal_id_seq", allocationSize = 1)
  @Column(name = "goal_id", nullable = false)
  private Long id;

  @Size(max = 100)
  @NotNull
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "target_amount", nullable = false, precision = 10, scale = 5)
  private BigDecimal targetAmount;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "current_amount", nullable = false, precision = 10, scale = 5)
  private BigDecimal currentAmount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id")
  private Wallet wallet;

  @Size(max = 200)
  @Column(name = "attachment_url", length = 200)
  private String attachmentUrl;

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

  @OneToMany(mappedBy = "goal")
  private Set<Trx> trxes = new LinkedHashSet<>();

}