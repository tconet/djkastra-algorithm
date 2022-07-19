package com.algorithm.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class City {

    private String name;
    private Integer estimate;
    private boolean isOpen;
    private City from;

    /**
     * Fecha a cidade
     */
    public void close() {
        this.isOpen = false;
    }

    public void updateBetterEstimate(City from, Integer estimate) {
        this.from = from;
        this.setEstimate(estimate);
    }

    /**
     * <p>
     *  Colocar a cididade como origem do destino, significa atualizar a sua estimativa
     *  para zero, e deixa-lá aberta.
     */
    public void setAsFirstCity() {
        this.estimate = 0;
        this.isOpen = true;
    }

    /**
     * <p>
     *  Inicializar a cidade, significa coloar a sua estimativa
     *  para infinito, e deixa-lá aberta.
     */
    public void initialize() {
        this.estimate = Integer.MAX_VALUE;
        this.isOpen = true;
    }

    /**
     * <p>
     * Campara as cidades pelo nome, se for igual, retorna true,
     * do contrário retorna false
     * @param city Cidade a ser comparada.
     * @return true se as cidades são iguais.
     */
    public boolean isEquals(City city) {
        return this.equals(city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
