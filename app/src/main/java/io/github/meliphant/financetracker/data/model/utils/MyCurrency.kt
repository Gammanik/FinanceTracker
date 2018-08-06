package io.github.meliphant.financetracker.data.model.utils

enum class MyCurrency {
    USD {
        override val rate = 1
        override val sign = '$'
    },
    RUB {
        override val rate = 60
        override val sign = '₽'
    };

    abstract val rate: Int //todo: delete?
    abstract val sign: Char
}