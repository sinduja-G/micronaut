package example.micronaut;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Body;
import jakarta.inject.Inject;
import java.util.List;

@Controller("/products") // Base URL for product endpoints
public class ProductController {

    @Inject
    ProductService productService;

    @Post("/") // Endpoint to create a new product
    public HttpResponse<String> addProduct(@Body Product product) {
        productService.createProduct(product.getId(), product.getCode(), product.getName());
        return HttpResponse.created("Product created!"); // 201 Created response
    }

    @Get("/{id}") // Endpoint to get a product by ID
    public HttpResponse<Product> getProduct(Long id) {
        return productService.getProduct(id)
                .map(product -> HttpResponse.ok(product)) // 200 OK response with product data
                .orElse(HttpResponse.notFound()); // 404 Not Found if product doesn't exist
    }

    @Get("/") // Endpoint to get all products
    public HttpResponse<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return HttpResponse.ok(products); // 200 OK response with list of products
    }

    @Put("/{id}") // Endpoint to update an existing product
    public HttpResponse<String> updateProduct(Long id, @Body Product product) {
        productService.updateProduct(id, product.getCode(), product.getName());
        return HttpResponse.ok("Product updated!"); // 200 OK response
    }

    @Delete("/{id}") // Endpoint to delete a product by ID
    public HttpResponse<String> deleteProduct(Long id) {
        productService.deleteProduct(id);
        return HttpResponse.ok("Product deleted!"); // 200 OK response
    }
}

