package verduleriaaa.verdu2a.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import verduleriaaa.verdu2a.model.Product;
import verduleriaaa.verdu2a.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("api/product")
@Tag(name = "Product", description = "Gestión de Productos de la Verdulería")

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "View a list of available products")
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by Id")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new product")
    public Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct != null) {
            existingProduct.setNombre(product.getNombre());
            existingProduct.setDescription(product.getDescription());

            return productService.saveProduct(existingProduct);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
