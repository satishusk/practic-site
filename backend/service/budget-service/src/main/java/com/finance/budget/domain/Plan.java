package com.finance.budget.domain;

import com.finance.budget.domain.amount.Amount;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@MappedSuperclass
public class Plan extends DependentByUserEntity {

  @Embedded
  @Column(name = "limit")
  private Amount limit;

  @Enumerated(EnumType.STRING)
  @Column(name = "period")
  private Period period;

  public Plan(Long userId,
              Amount limit,
              Period period) {
    super(userId);
    this.limit = limit;
    this.period = period;
  }

  public Plan() {}

  public static class Builder extends DependentByUserEntity.Builder {
    protected Amount limit;
    protected Period period;

    public Builder limit(Amount limit) {
      this.limit = limit;
      return this;
    }

    public Builder period(Period period) {
      this.period = period;
      return this;
    }

    public Plan build() {
      return new Plan(userId, limit, period);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Plan plan = (Plan) o;
    return Objects.equals(limit, plan.limit) && period == plan.period;
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, period);
  }
}
