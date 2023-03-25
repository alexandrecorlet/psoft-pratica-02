package com.ufcg.psoft.mercadofacil.repository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ufcg.psoft.mercadofacil.model.Lote;

@Repository
public class VolatilLoteRepository implements LoteRepository<Lote, Long> {

    List<Lote> lotes = new ArrayList<>();

    @Override
    public Lote save(Lote lote) {
        lotes.add(lote);
        return lotes.get(lotes.size() - 1);
    }

    @Override
    public Lote find(Long id) {
        Lote resultado = null;
        for (Lote lote : lotes)
            if (lote.getId().equals(id))
                resultado = lote;
        return resultado;
    }

    @Override
    public List<Lote> findAll() {
        return lotes;
    }

    @Override
    public Lote update(Lote lote) {

        for (int i = 0; i < lotes.size(); ++i) {
            if (lotes.get(i).getId().equals(lote.getId())) {
                lotes.set(i, lote);
                return lote;
            }
        }

        return null;
    }

    @Override
    public void delete(Lote lote) {
        lotes.remove(lote);
    }

    @Override
    public void deleteAll() {
        lotes.clear();
    }

}
