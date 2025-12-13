package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.*;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Products> getLatestProduct(int limit){
        return productRepository.findLatestProducts(Pageable.ofSize(limit));
    }

    public List<Products> getSaleProduct(int limit){
        return productRepository.findSaleProduct(Pageable.ofSize(limit));
    }

    //Lấy sản phẩm Iphone
    public List<Products> getProductByCategory(String categoryName){
        return productRepository.findByCategoryName(categoryName);
    }

    //Lấy thông tin chi tiết sản phẩm
    public List<ProductVariant> getVariantByProductId(Long sku){
        return productVariantRepository.findByProductWithDetail(sku);

    }

    //Lấy sản phẩm theo ID
    public Products getProductById(Long id){
        Optional<Products> product = productRepository.findById(id);
        return product.orElse(null);
    }


    //Lấy danh sách cmt đánh giá của user
    public List<Review> getReviewByProduct(Long productId){
        return reviewRepository.findByProductId(productId);
    }

    public void saveReview (Long userId, Long productId, int rating, String content){
        Review review = new Review();
        review.setUserId(userId);
        review.setProductId(productId);
        review.setRating(rating);
        review.setContent(content);
        review.setLikes(0);
        review.setCreatedAt(LocalDate.now());
        reviewRepository.save(review);

    }

    // ✅ Lấy danh sách sản phẩm trong giỏ hàng của user
    public List<CartItem> getCartItems(User user) {
        // 1️⃣ Lấy giỏ hàng của user
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElse(null);

        // 2️⃣ Nếu chưa có giỏ hàng thì trả danh sách rỗng
        if (cart == null) {
            return List.of();
        }

        // 3️⃣ Lấy danh sách item trong giỏ hàng
        return cartItemRepository.findByCartId(cart.getId());
    }


    //Tạo và lấy sách giỏ hàng
    public Cart getOrCreateCart(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    //tính tổng tiền
    // ✅ Tính tổng tiền trong giỏ hàng
    public BigDecimal getTotal(User user) {
        Cart cart = cartRepository.findByUserId(user.getId()).orElse(null);

        if (cart == null) {
            return BigDecimal.ZERO; // chưa có giỏ hàng thì tổng = 0
        }

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        // Duyệt qua từng item và cộng dồn subtotal
        BigDecimal total = items.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total;
    }


    public void addToCart(User user, Long productId, Long variantId, int quantity) {
        Cart cart = getOrCreateCart(user);
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductVariant variant = (variantId != null)
                ? productVariantRepository.findById(variantId).orElse(null)
                : null;

        Optional<CartItem> existingItem = cartItemRepository.findByCartId(cart.getId()).stream()
                .filter(i ->
                        i.getProducts().getId().equals(productId) &&
                                ((variant == null && i.getVariant() == null) ||
                                        (variant != null && i.getVariant() != null &&
                                                i.getVariant().getId().equals(variantId)))
                )
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProducts(product);
            newItem.setVariant(variant);
            newItem.setQuantity(quantity);
            newItem.setUnitPrice(variant != null ? variant.getPrice() : product.getPrice());
            cartItemRepository.save(newItem);
        }
    }

    public void updateQuantity(Long cartItemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        }
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public void removeItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new RuntimeException("Cart item not found");
        }
        cartItemRepository.deleteById(cartItemId);
    }






}

