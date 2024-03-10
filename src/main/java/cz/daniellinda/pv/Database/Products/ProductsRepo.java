package cz.daniellinda.pv.Database.Products;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Products repo.
 */
public interface ProductsRepo extends JpaRepository<Products, Long> {
}
