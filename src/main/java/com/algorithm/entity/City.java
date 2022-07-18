package com.algorithm.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {

    private String name;
    private Integer estimate;
    private boolean isOpen;
    private City from;

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

}
