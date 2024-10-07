
package example.micronaut;


import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {





}
