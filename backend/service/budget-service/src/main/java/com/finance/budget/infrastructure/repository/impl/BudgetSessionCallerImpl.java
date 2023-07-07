package com.finance.budget.infrastructure.repository.impl;

import com.finance.budget.domain.Budget;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BudgetSessionCallerImpl implements SessionCaller<Budget> {
  private final SessionFactory sessionFactory;

  @Autowired
  public BudgetSessionCallerImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Budget> findSample(Long userId, Integer offset, Integer count) {
    String hql = "from Budget where userId = :userId";
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery(hql, Budget.class)
        .setParameter("userId", userId)
        .setFirstResult(offset)
        .setMaxResults(count)
        .list();
    }
  }
}
