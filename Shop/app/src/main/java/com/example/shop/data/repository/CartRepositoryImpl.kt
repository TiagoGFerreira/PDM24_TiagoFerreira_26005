package com.example.shop.data.repository

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
            } catch (_: Exception) {
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
                }
            } catch (_: Exception) {
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

    override suspend fun shareCartItems(emailOtherUser: String): String {
        val currentUserEmail = userEmail
        if (currentUserEmail.isNullOrEmpty()) {
            return "User email is null or empty."
        }

        try {
            val otherUserSnapshot = cart.document(emailOtherUser).get().await()

            if (!otherUserSnapshot.exists()) {
                return "The specified user does not exist."
            }

            val currentUserCartSnapshot = cart.document(currentUserEmail).get().await()

            if (!currentUserCartSnapshot.exists()) {
                return "Current user cart does not exist."
            }

            val currentUserCartItems = currentUserCartSnapshot.toObject(Cart::class.java)?.products.orEmpty()

            if (currentUserCartItems.isEmpty()) {
                return "Current user cart is empty or null."
            }

            val otherUserCartRef = cart.document(emailOtherUser)
            otherUserCartRef.update("products", currentUserCartItems).await()

            return "Cart successfully shared to $emailOtherUser."

        } catch (e: Exception) {
            return "Error sharing cart items: ${e.message}"
        }
    }
}
