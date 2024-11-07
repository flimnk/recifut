package com.example.demo.service;

import com.example.demo.domain.product.*;
import com.example.demo.infra.excepitions.ValidaRegraDeNegocios;
import com.example.demo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest request) {
        productRepository.findByName(request.name()).ifPresent(ex -> {
            throw new RuntimeException("Product with the name '" + request.name() + "' already exists");
        });
        System.out.println(request);
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setClube(request.clube());
        product.setMarca(request.marca());
        product.setOldPrice(request.oldPrice());
        product.setNewPrice(request.newPrice());
        product.setEstrelas(request.estrelas());
        product.setSizes(request.sizes());
        product.setTotalRatings(request.totalRatings());
        product.setPromocao(request.promocao());
        product.setImage1Url(request.image1Url());
        product.setImage2Url(request.image2Url());
        product.setImage3Url(request.image3Url());

        System.out.println(product);

        productRepository.save(product);

        return new ProductResponse(
                product
        );
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ValidaRegraDeNegocios("Product not found"));
        return new ProductResponse(
                product
        );
    }

    public Page<ProductResponse> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponse::new);
    }

    public ProductResponse updateProduct(ProductUpdateRequest updateRequest) {
        Product existingProduct = productRepository.findByName(updateRequest.name())
                .orElseThrow(() -> new ValidaRegraDeNegocios("Product " + updateRequest.name() + " not found"));

        applyUpdatesToProduct(existingProduct, updateRequest);

        productRepository.save(existingProduct);

        return new ProductResponse(existingProduct);
    }

    private void applyUpdatesToProduct(Product product, ProductUpdateRequest updateRequest) {
        if (updateRequest.name() != null) product.setName(updateRequest.name());
        if (updateRequest.description() != null) product.setDescription(updateRequest.description());
        if (updateRequest.category() != null) product.setCategory(updateRequest.category());
        if (updateRequest.clube()!= null) product.setClube(updateRequest.clube());
        if (updateRequest.marca() != null) product.setMarca(updateRequest.marca());
        if (updateRequest.oldPrice() != null) product.setOldPrice(updateRequest.oldPrice());
        if (updateRequest.newPrice() != null) product.setNewPrice(updateRequest.newPrice());
        if (updateRequest.estrelas() != null) product.setEstrelas(updateRequest.estrelas());
        if (updateRequest.sizes() != null) product.setSizes(updateRequest.sizes());
        if (updateRequest.totalRatings() != null) product.setTotalRatings(updateRequest.totalRatings());
        if (updateRequest.promocao() != null) product.setPromocao(updateRequest.promocao());
    }

    public void deleteProduct(ProductDeleteRequest productDeleteRequest) {

        if (!productRepository.existsByName(productDeleteRequest.name())) {
            throw new ValidaRegraDeNegocios("Product not found");
        }
        productRepository.delete(productRepository.findByName(productDeleteRequest.name()).get());
    }

    public List<ProductResponse> getProductByCategory(String category) {
        System.out.println(category.isEmpty());
       var lista =    productRepository.findAllByCategory(category);
      if( lista.isEmpty()){
          throw new ValidaRegraDeNegocios("Nenhum produto da categoria: " + category);
      }
     return lista.stream().map(ProductResponse::new).toList();


    }
}
