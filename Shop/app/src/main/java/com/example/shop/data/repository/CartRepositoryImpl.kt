package com.example.shop.data.repository

import android.util.Log
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.CartItem
import com.example.shop.domain.repository.CartRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor() : CartRepository {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val cart: CollectionReference = db.collection("cart")

    private val userEmail: String?
        get() = auth.currentUser?.email

    override suspend fun addItemCart(cartItem: CartItem) {
        userEmail?.let { email ->
            val userCartRef = cart.document(email)

            try {
                val snapshot = userCartRef.get().await()

                if (snapshot.exists()) {
                    val currentCart = snapshot.toObject(Cart::class.java)
                    val products = currentCart?.products?.toMutableList() ?: mutableListOf()

                    val existingProduct = products.find { it.id == cartItem.id }
                    if (existingProduct != null) {
                        existingProduct.quantity += cartItem.quantity
                    } else {
                        products.add(cartItem)
                    }

                    userCartRef.update("products", products).await()
                } else {
                    val newCart = Cart(email, listOf(cartItem))
                    userCartRef.set(newCart).await()
                }
            } catch (e: Exception) {
                Log.e("CartRepository", "Error adding item to cart: ${e.message}", e)
            }
        }
    }

    override suspend fun removeItemCart(cartItem: CartItem) {
        userEmail?.let { email ->
            val userCartRef = db.collection("cart").document(email)

            try {
                val snapshot = userCartRef.get().await()

                if (snapshot.exists()) {
                    val productsList = snapshot.toObject(Cart::class.java)?.products?.toMutableList() ?: mutableListOf()

                    val productToRemove = productsList.find { it.id == cartItem.id }

                    if (productToRemove != null) {
                        productsList.remove(productToRemove)
                        userCartRef.update("products", productsList).await()
                    }
                    else{
                        Log.e("CartRepository", "Product does not exist")
                    }
                }
                else{
                    Log.e("CartRepository", "Cart does not exist")
                }
            } catch (e: Exception) {
                Log.e("CartRepository", "Error removing item from cart: ${e.message}", e)
            }
        }
    }

    override suspend fun clearCart(cart: Cart) {
        userEmail?.let { email ->
            val userCartRef = db.collection("cart").document(email)

            try {
                val snapshot = userCartRef.get().await()

                if (snapshot.exists()) {
                    userCartRef.update("products", mutableListOf<CartItem>()).await()
                }
            } catch (_: Exception) {
            }
        }
    }

    override suspend fun getTotalItems(): Int {
        return userEmail?.let { email ->
            try {
                val snapshot = cart.document(email).get().await()

                if (snapshot.exists()) {
                    val products = snapshot.toObject(Cart::class.java)?.products.orEmpty()
                    return products.sumOf { it.quantity }
                } else {
                    return 0
                }
            } catch (_: Exception) {
                return 0
            }
        } ?: 0
    }

    override suspend fun getCartItems(): List<CartItem> {
        return userEmail?.let { email ->
            try {
                val snapshot = cart.document(email).get().await()
                if (snapshot.exists()) {
                    val products = snapshot.toObject(Cart::class.java)?.products.orEmpty()
                    return products
                } else {
                    return emptyList()
                }
            } catch (_: Exception) {
                return emptyList()
            }
        } ?: emptyList()
    }

    override suspend fun shareCartIems(emailOtherUser: String, cart: Cart) {
        userEmail?.let {
            try {
                val snapshot = this.cart.document(emailOtherUser).get().await()

                if (snapshot.exists()) {
                    val cartItems: List<CartItem> = snapshot.toObject(Cart::class.java)?.products.orEmpty()
                    clearCart(cart)
                    cartItems.forEach { item ->
                        addItemCart(item)
                    }
                }
                else{
                    Log.e("CartRepository", "Cart does not exist")
                }
            } catch (e: Exception) {
                Log.e("CartRepository", "Error sharing cart items: ${e.message}", e)
            }
        }
    }
}
