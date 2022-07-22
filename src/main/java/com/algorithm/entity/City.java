package com.algorithm.entity;
import java.util.ArrayList;
import java.util.Objects;

import lombok.Data;

@Data
public class City {

    private String name;
    private Integer estimate = 0;
    private boolean isOpen;
    private City from;
    private ArrayList<Route> routes = new ArrayList<Route>();

    public City(String name) {
        this.name = name;
    }

    /**
     * Fecha a cidade
     */
    public void close() {
        this.isOpen = false;
    }

    public Integer getPath(String to) {

        for (Route route: routes) {
            if ( route.getTo().equals(to)) {
                return route.getDistance();
            }
        }
        return 0;
    }

    /**
     * <p>
     * Add a route
     * @param route The route to be added.
     */
    public void populateRoutes(Route route){
        this.routes.add(route);
    }

    /**
     * <p>
     * Atualiza a melhor estimativa, e indica de qual cidade veio.
     * @param from Cidade de onde veio
     * @param estimate Valor da menor estimativa
     */
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
