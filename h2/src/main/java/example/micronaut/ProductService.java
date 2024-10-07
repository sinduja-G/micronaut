package example.micronaut;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Singleton
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Transactional
    public void createProduct(Long id, String code, String name) {
        Product product = new Product(id, code, name); // Create a new Product object
        productRepository.save(product); // Persist the new product to the database
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id); // Retrieve product by ID
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Retrieve all products
    }

    @Transactional
    public void updateProduct(Long id, String code, String name) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setCode(code); // Update the code
            product.setName(name); // Update the name
            productRepository.update(product); // Save the updated product
        }
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id); // Delete the product by ID
    }
}
