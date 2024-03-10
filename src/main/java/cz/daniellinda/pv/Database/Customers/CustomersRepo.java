package cz.daniellinda.pv.Database.Customers;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Customers repo.
 */
public interface CustomersRepo extends JpaRepository<Customers, Long> {
}
