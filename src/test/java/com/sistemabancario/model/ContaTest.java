package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    @Test
    void testSetNumeroValido(){
        final Conta instance = new Conta();
        final String valido = "12345-6";
        instance.setNumero(valido);
        final String obtido = instance.getNumero();
        assertEquals(valido, obtido);
    }
    @Test
    void testSetNumeroInvalidoNaoArmazena(){
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));
        final String obtido = instance.getNumero();
        assertNotEquals(invalido, obtido);
    }

    @Test //Numero Nulo
    void testSetNumeroNull(){
        final Conta instance = new Conta();
        assertThrows(NullPointerException.class, () -> instance.setNumero(null));
    }

    /* Testes do R2 */

    @Test
    void testInstanciaPadraoPoupanca(){
        final Conta instance = new Conta();
        assertFalse(instance.isPoupanca());
    }
    /* Testes do R3 */

    @Test
    void testSetLimiteContaEspecial(){
        final Conta instance = new Conta();
        instance.setEspecial(true);
        final double esperado = 1000;
        instance.setLimite(esperado);
        final double obtido = instance.getLimite();
        assertEquals(esperado, obtido);
    }

    @Test
    void testSetLimiteContaNaoEspecial(){
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
        final double obtido = instance.getLimite();
        assertNotEquals(limite, obtido);
    }

    /* Testes do R4 */

    @Test
    void testHistoricoNotNull(){
        final Conta instance = new Conta();
        assertNotNull(instance.getMovimentacoes());
    }
    @Test
    void testGetSaldoTotal(){
        final double limite = 500;
        final double esperado = limite;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido);
    }

    @Test
    void testDepositoDinheiro(){
        final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);

        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.001);
    }

    /* Movimentação adicionada na lista */
    @Test
    void testAddMovimentacaoCredito() {
        Conta instance = new Conta();
        Movimentacao mv = new Movimentacao(instance);
        mv.setConfirmada(true);
        mv.setTipo('C');
        final double esperado = 100.50;
        mv.setValor(esperado);
        instance.addMovimentacao(mv);
        assertEquals(esperado, instance.getSaldoTotal());
    }

    /* Verifica se tipo movimentação foi definida como credito */
    @Test
    void testTipoMovimentacaoCredito() {
        Conta instance = new Conta();
        Movimentacao mv = new Movimentacao(instance);
        mv.setConfirmada(true);
        mv.setTipo('C');
        final char esperado = 'C';
        instance.addMovimentacao(mv);
        assertEquals(esperado, mv.getTipo());
    }

    /* Teste de valor atribuído */
    @Test
    void testValorAtribuido() {
        Conta instance = new Conta();
        Movimentacao mv = new Movimentacao(instance);
        final double esperado = 200;
        mv.setConfirmada(true);
        mv.setTipo('C');
        mv.setValor(esperado);
        instance.addMovimentacao(mv);
        assertEquals(esperado, mv.getValor());
    }

    /* Teste Deposito confirmado */
    @Test
    void testDepositoConfirmado() {
        Conta instance = new Conta();
        Movimentacao mv = new Movimentacao(instance);
        final boolean esperado = true;
        mv.setConfirmada(true);
        mv.setTipo('C');
        instance.addMovimentacao(mv);
        assertEquals(esperado, mv.isConfirmada());
    }

    //Deposito de Valor Negativo
    @Test
    void testDepositoDinheiroValorNegativo(){
        final double limite = 500.6, deposito = -500;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        assertThrows(IllegalArgumentException.class, () -> instance.depositoDinheiro(deposito));
    }
}
