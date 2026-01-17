package pl.bank.s33840bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.bank.s33840bank.api.Status;
import pl.bank.s33840bank.model.Client;
import pl.bank.s33840bank.store.ClientStore;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankServiceTest {

    private ClientStore store;
    private BankService service;

    @BeforeEach
    void setup() {
        store = Mockito.mock(ClientStore.class);
        service = new BankService(store);
    }

    @Test
    void transfer_declined_when_not_enough_money() {
        Client c = new Client(1L, new BigDecimal("10"));
        when(store.findById(1L)).thenReturn(Optional.of(c));

        var res = service.transfer(1L, new BigDecimal("20"));

        assertEquals(Status.DECLINED, res.getStatus());
        verify(store, never()).save(any());
    }

    @Test
    void deposit_declined_when_client_not_found() {
        when(store.findById(1L)).thenReturn(Optional.empty());

        var res = service.deposit(1L, new BigDecimal("10"));

        assertEquals(Status.DECLINED, res.getStatus());
        assertNull(res.getBalance());
        verify(store, never()).save(any());
    }

    @Test
    void deposit_accepted_updates_balance() {
        Client c = new Client(1L, new BigDecimal("10"));
        when(store.findById(1L)).thenReturn(Optional.of(c));
        when(store.save(any())).thenAnswer(inv -> inv.getArgument(0));

        var res = service.deposit(1L, new BigDecimal("5"));

        assertEquals(Status.ACCEPTED, res.getStatus());
        assertEquals(new BigDecimal("15"), res.getBalance());
        verify(store, times(1)).save(any());
    }

}
