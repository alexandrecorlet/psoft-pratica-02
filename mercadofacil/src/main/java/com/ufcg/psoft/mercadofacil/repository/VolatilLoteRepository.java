package com.ufcg.psoft.mercadofacil.repository;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.ufcg.psoft.mercadofacil.model.Lote;

@Repository
public class VolatilLoteRepository implements LoteRepository<Lote, Long> {

    Map<Long, Lote> lotes = new HashMap<>();

    @Override
    public Lote save(Lote lote) {
        lotes.put(lote.getId(), lote);
        return lote;
    }

    @Override
    public Lote find(Long id) {
        return lotes.getOrDefault(id, null);
    }

    @Override
    public Collection<Lote> findAll() {
        return lotes.values();
    }

    @Override
    public Lote update(Lote lote) {
        Long id = lote.getId();
        return (containsLote(id)) ? lotes.replace(id, lote) : null;
    }

    @Override
    public void delete(Lote lote) {
        lotes.remove(lote.getId());
    }

    @Override
    public void deleteAll() {
        lotes.clear();
    }

    private boolean containsLote(Long id) {
        return lotes.containsKey(id);
    }

}
