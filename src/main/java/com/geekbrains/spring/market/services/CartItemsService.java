package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.cartItems.CartItems;
import com.geekbrains.spring.market.entity.cartItems.CookieCartItem;
import com.geekbrains.spring.market.entity.carts.Cart;
import com.geekbrains.spring.market.entity.cartItems.CartItem;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.carts.CookieCart;
import com.geekbrains.spring.market.repositories.CartItemRepository;
import com.geekbrains.spring.market.repositories.CookieCartItemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CartItemsService {

    private CartItemRepository cartItemRepository;
    private CookieCartItemRepository cookieCartItemRepository;

    @Autowired
    public void setCookieCartItemRepository(CookieCartItemRepository cookieCartItemRepository) {
        this.cookieCartItemRepository = cookieCartItemRepository;
    }

    @Autowired
    public void setCartItemRepository(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem addProductToCart(Product product,Cart cart){
        CartItem cartItem = cartItemRepository.findFirstByProductAndCart(product,cart)
                .orElse(getNewCartItem(product,cart));
        cartItem.setQuantity(cartItem.getQuantity()+1);
        return cartItemRepository.save(cartItem);
    }

    public CookieCartItem addProductToCookieCart(Product product, CookieCart cookieCart){
        CookieCartItem cookieCartItem = cookieCartItemRepository
                .findFirstByProductAndCookieCart(product,cookieCart)
                .orElse(getNewCookieCartItem(product,cookieCart));
        cookieCartItem.setQuantity(cookieCartItem.getQuantity()+1);
        return cookieCartItemRepository.save(cookieCartItem);
    }

    public void convertCookieCartItemToCartItem(CookieCart cookieCart, Cart cart){
        List<CookieCartItem> cookieCartItems = cookieCartItemRepository.findAllByCookieCart(cookieCart);
        cookieCartItems.forEach(cookieCartItem -> {
            CartItem cartItem = cartItemRepository
                    .findFirstByProductAndCart(cookieCartItem.getProduct(),cart)
                    .orElse(getNewCartItem(cookieCartItem.getProduct(),cart));
            cartItem.setQuantity(cartItem.getQuantity()+cookieCartItem.getQuantity());
            cartItemRepository.save(cartItem);
        });
    }

    public Map<Product,CartItems> findCartMap(Cart cart){
        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        return cartItems
                .stream()
                .collect(Collectors.toMap(CartItem::getProduct,Function.identity()));
    }

    public Map<Product,CartItems> findCookieCartMap(CookieCart cookieCart){
        List<CookieCartItem> cookieCartItems = cookieCartItemRepository.findAllByCookieCart(cookieCart);
        return cookieCartItems
                .stream()
                .collect(Collectors.toMap(CookieCartItem::getProduct, Function.identity()));
    }

    private CookieCartItem getNewCookieCartItem(Product product,CookieCart cookieCart){
        return new CookieCartItem().setProduct(product).setCookieCart(cookieCart).setQuantity(0);
    }

    private CartItem getNewCartItem(Product product,Cart cart){
        return new CartItem().setProduct(product).setCart(cart).setQuantity(0);
    }
}
