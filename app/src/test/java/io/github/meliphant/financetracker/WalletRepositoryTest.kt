package io.github.meliphant.financetracker

import io.github.meliphant.financetracker.data.model.Money
import io.github.meliphant.financetracker.data.model.Wallet
import io.github.meliphant.financetracker.data.model.dao.WalletDao
import io.github.meliphant.financetracker.data.model.utils.MyCurrency
import io.github.meliphant.financetracker.data.repository.WalletRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class WalletRepositoryTest {


    private lateinit var walletDao: WalletDao
    private lateinit var walletRepository: WalletRepository

    private val tstWall = Wallet(1, "tstWall1-USD", Money(0.0, MyCurrency.USD), "some")

    @Before
    fun setUp() {
        walletDao = Mockito.mock(WalletDao::class.java)
        walletRepository = WalletRepository(walletDao)
    }

    @Test
    fun shouldGetWallets() {
        `when`(walletDao.getWallets()).thenReturn(listOf<Wallet>())
        walletRepository.getAllWallets()

        verify(walletDao).getWallets()
        verifyNoMoreInteractions(walletDao)
    }

    @Test
    fun shouldGetWalletById() {
        `when`(walletDao.getWalletById(tstWall.walletId)).thenReturn(tstWall)
        walletRepository.getWalletById(tstWall.walletId)

        verify(walletDao).getWalletById(tstWall.walletId)
        verifyNoMoreInteractions(walletDao)
    }


}