package com.tul.carshop.routes

class Router{
    companion object {

        //base paths
        const val BASE_ROUTE = "/api/ecommerce"
        const val INFO = "/info"
        const val HELATH_CHECK = "/health"

        //paths for products
        const val PRODUCTS = "/products"
        const val PRODUCT_ADD = "/create"
        const val PRODUCT_PUT = "/modify"
        const val PRODUCT_BY_ID =  "/find_by_id/{id}"
        const val PRODUCT_DELETE_BY_ID =  "/delete_by_id/{id}"

        //paths for shopping car
        const val BASE_CAR = "/shopping_car"
        const val SHOW_ALL_PRODUCTS_FOR_ALL_CLIENTS = "/show_all"
        const val SHOW_BY_ID = "/find_by_id/{id}"
        const val LIST_BY_CLIENT = "/{cl}"
        const val ADD_TO_CAR = "/add_to_car"
        const val EDIT_CAR = "/edit_product_car/{cl}"
        const val DELETE_PRODUCT_CAR = "/delete_product/{id}"
        const val CLEAN_CAR = "/clean_car/{cl}"

    }
}