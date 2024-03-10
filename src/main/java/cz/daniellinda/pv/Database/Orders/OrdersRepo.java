package cz.daniellinda.pv.Database.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Orders repo.
 */
public interface OrdersRepo extends JpaRepository<Orders, Long> {
}
