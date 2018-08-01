package io.github.meliphant.financetracker.repository.model.utils

enum class MyCategory {
    Travel {
        override val iconUrl = "category_travel"
    },
    Restaurants {
        override val iconUrl = "category_restaurants"
    },
    Groceries {
        override val iconUrl = "category_groceries"
    };

    abstract val iconUrl: String
}