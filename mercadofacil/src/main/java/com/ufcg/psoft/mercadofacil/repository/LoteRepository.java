package com.ufcg.psoft.mercadofacil.repository;

import java.util.Collection;

public interface LoteRepository<T, ID> {

    T save(T lote);

    T find(ID lote);

    Collection<T> findAll();

    T update(T lote);

    void delete(T lote);

    void deleteAll();

}
