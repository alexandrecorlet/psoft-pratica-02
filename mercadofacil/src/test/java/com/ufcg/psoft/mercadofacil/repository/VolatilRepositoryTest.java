package com.ufcg.psoft.mercadofacil.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;

@SpringBootTest
public class VolatilRepositoryTest {

    @Autowired
    LoteRepository<Lote, Long> driver;

    Lote lote;

    Lote resultado;

    Lote loteExtra;

    Produto produto;

    Produto produtoExtra;

    @BeforeEach
    void setup() {
        produto = Produto.builder()
                .id(1L)
                .nome("Produto Base")
                .codigoBarra("123456789")
                .fabricante("Fabricante Base")
                .preco(125.36)
                .build();

        lote = Lote.builder()
                .id(1L)
                .numeroDeItens(100)
                .produto(produto)
                .build();

        produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();

        loteExtra = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produtoExtra)
                .build();

    }

    @AfterEach
    void tearDown() {
        produto = null;
        driver.deleteAll();
    }

    @Test
    @DisplayName("Adicionar o primeiro Lote no repositorio de dados")
    void salvarPrimeiroLote() {
        resultado = driver.save(lote);
        assertEquals(1, driver.findAll().size());
        assertEquals(lote.getId().longValue(), resultado.getId().longValue());
        assertEquals(produto, resultado.getProduto());
    }

    @Test
    @DisplayName("Adicionar o segundo lote (ou posterior) no repositorio de dados")
    void salvarSegundoOuLotePosterior() {
        driver.save(lote);
        resultado = driver.save(loteExtra);

        assertEquals(2, driver.findAll().size());
        assertEquals(loteExtra.getId().longValue(), resultado.getId().longValue());
        assertEquals(produtoExtra, resultado.getProduto());
    }

    @Test
    @DisplayName("Procurar o lote no repositorio de dados vazio")
    void procurarLoteNoRepositorioVazio() {
        driver.deleteAll();
        resultado = driver.find(lote.getId());
        assertNull(resultado);
    }

    @Test
    @DisplayName("Procurar o lote no repositorio de dados com apenas um lote")
    void procurarLoteTC01() {
        driver.deleteAll();
        driver.save(lote);
        resultado = driver.find(lote.getId());
        assertEquals(lote.getId().longValue(), resultado.getId().longValue());
        assertEquals(produto, resultado.getProduto());
    }

    @Test
    @DisplayName("Procurar lote no repositorio de dados com um ou mais lotes")
    void procurarLoteTC02() {
        driver.save(lote);
        driver.save(loteExtra);
        resultado = driver.find(loteExtra.getId());
        assertEquals(loteExtra.getId().longValue(), resultado.getId().longValue());
        assertEquals(produtoExtra, resultado.getProduto());
    }

    @Test
    @DisplayName("Procurar lote que não está no repositorio de dados")
    void procurarLoteTC03() {
        driver.save(lote);
        resultado = driver.find(loteExtra.getId());
        assertNull(resultado);
    }

    @Test
    @DisplayName("Listar lotes que estao no repositorio de dados vazio")
    void listarLotesNoRepositorioVazio() {
        driver.deleteAll();
        assertTrue(driver.findAll().isEmpty());
    }

    @Test
    @DisplayName("Listar lotes que estao no repositorio de dados")
    void listarLotesTC01() {
        driver.save(lote);
        driver.save(loteExtra);
        assertEquals(2, driver.findAll().size());
    }

    @Test
    @DisplayName("Atualizar lote que não está no repositório de dados")
    void atualizarLoteRepositorioVazio() {
        driver.deleteAll();
        resultado = driver.update(lote);
        assertNull(resultado);
    }

    @Test
    @DisplayName("Atualizar lote no repositório de dados")
    void atualizarLoteTC01() {
        driver.save(lote);
        driver.save(loteExtra);
        lote.setNumeroDeItens(1234);
        resultado = driver.update(lote);
        assertEquals(lote.getId().longValue(), resultado.getId().longValue());
        assertEquals(lote.getProduto(), resultado.getProduto());
        assertEquals(1234, resultado.getNumeroDeItens());
    }

    @Test
    @DisplayName("Remove lote de um repositório de dados vazio")
    void removerLoteDoRepositorioVazio() {
        driver.deleteAll();
        driver.delete(lote);
        assertTrue(driver.findAll().isEmpty());
    }

    @Test
    @DisplayName("Remove lote de um repositório de dados com apenas um lote")
    void removerLoteTC01() {
        driver.save(lote);
        driver.delete(lote);
        assertTrue(driver.findAll().isEmpty());
    }

    @Test
    @DisplayName("Remove lote de um repositório de dados")
    void removerLoteTC02() {
        driver.save(lote);
        driver.save(loteExtra);

        assertEquals(2, driver.findAll().size());
        driver.delete(lote);

        assertNull(driver.find(lote.getId()));
        assertEquals(1, driver.findAll().size());

    }

    @Test
    @DisplayName("Remove todos os lotes de um repositório de dados vazio")
    void removerTodosLotesRepositorioVazio() {
        driver.deleteAll();
        assertTrue(driver.findAll().isEmpty());
    }

    @Test
    @DisplayName("Remove todos os lotes de um repositório de dados")
    void removerTodosLotesTC01() {
        driver.save(lote);
        driver.save(loteExtra);
        driver.deleteAll();
        assertTrue(driver.findAll().isEmpty());
    }

}
