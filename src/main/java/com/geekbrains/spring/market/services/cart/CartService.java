package com.geekbrains.spring.market.services.cart;

import com.geekbrains.spring.market.entity.cart.item.CartItems;
import com.geekbrains.spring.market.entity.cart.Cart;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.user.User;
import com.geekbrains.spring.market.entity.cart.CookieCart;
import com.geekbrains.spring.market.repositories.cart.CartRepository;
import com.geekbrains.spring.market.repositories.cart.CookieCartRepository;
import com.geekbrains.spring.market.services.cart.item.CartItemsService;
import com.geekbrains.spring.market.services.user.UserServiceImpl;
import com.geekbrains.spring.market.util.CookieUserHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Log4j2
public class CartService {

    private CartRepository cartRepository;
    private CookieCartRepository cookieCartRepository;
    private UserServiceImpl userService;
    private CartItemsService cartItemsService;

    @Autowired
    public void setCookieCartRepository(CookieCartRepository cookieCartRepository) {
        this.cookieCartRepository = cookieCartRepository;
    }

    @Autowired
    public void setCartItemsService(CartItemsService cartItemsService) {
        this.cartItemsService = cartItemsService;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public Map<Product, CartItems> getMapForPage(CookieUserHandler cookieUserHandler){
        if (cookieUserHandler.getCart()!=null) return cartItemsService
                .findCartMap(cookieUserHandler.getCart());
        if (cookieUserHandler.getCookieCart()!=null) return cartItemsService
                .findCookieCartMap(cookieUserHandler.getCookieCart());
        return null;
    }

    public boolean delete(CookieUserHandler cookieUserHandler){
        if (cookieUserHandler.getCart()!=null){
            cartRepository.delete(cookieUserHandler.getCart());
            cookieUserHandler.setCart(null);
            return true;
        }
        if (cookieUserHandler.getCookieCart()!=null) {
            cookieCartRepository.delete(cookieUserHandler.getCookieCart());
            cookieUserHandler.setCookieCart(null);
            return true;
        }
        return false;
    }

    public Cart getCartByUser(String username){
        User user = userService.findByUsername(username);
        Cart cart = cartRepository.findByUser(user).orElse(getNewCartByUser(user));
        if (cart.getId()==null){
            log.info("For User: "+ user.getUsername()+" create cart: " + cart);
            return cartRepository.save(cart);
        }
        return cart;
    }

    public void addProductToCart(Product product, CookieUserHandler cookieUserHandler){
        Cart cart = cookieUserHandler.getCart();
        CartItems cartItems;
        if (cart==null){
            CookieCart cookieCart = cookieUserHandler.getCookieCart();
            if (cookieCart==null){
                cookieCart = cookieCartRepository
                        .findBySessionId(cookieUserHandler.getSessionId())
                        .orElse(getNewCookieCartBySessionId(cookieUserHandler.getSessionId()));
                log.info("CookieCart create: " + cookieUserHandler
                        .setCookieCart(cookieCart)
                        .getCookieCart());
            }
            cartItems = cartItemsService.addProductToCookieCart(product,cookieCart);
        } else {
            cartItems = cartItemsService.addProductToCart(product,cart);
        }
        log.info("Add Product to cart:" + cartItems);
    }

    public void bindCart(CookieUserHandler cookieUserHandler,String username){
        Cart cart = getCartByUser(username);
        CookieCart cookieCart = cookieUserHandler.getCookieCart();
        if (cookieCart!=null){
            cartItemsService.convertCookieCartItemToCartItem(cookieCart,cart);
            cookieCartRepository.delete(cookieCart);
            cookieUserHandler.setCookieCart(null);
        }
        cookieUserHandler.setCart(cart);
    }

    private CookieCart getNewCookieCartBySessionId(String sessionId){
        return cookieCartRepository.save(new CookieCart()
                .setSessionId(sessionId));
    }

    private Cart getNewCartByUser(User user){
        return new Cart()
                .setUser(user);
    }
}


