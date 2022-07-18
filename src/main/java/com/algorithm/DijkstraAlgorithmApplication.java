package com.algorithm;

import com.algorithm.entity.City;
import com.algorithm.entity.Route;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class DijkstraAlgorithmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DijkstraAlgorithmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/** REQUSITOS */
		// ---------------------------------------------------------------------------------------

		/** Inicialização */
		// 1° Passo:
		// -- Inicilializa a estimativa da primeira cidade com zero.
		// -- As demais cidades terão a estimativa inicializada como infinito.
		// -- Colocar todas as cidades como aberta

		// 2° Passo: (Loop)
		// -- Enquanto tiver cidade aberta,
		// -- Buscar a cidade com menor estimativa.
		// -- Se encontrar fecha a cidade.

		// 3° Passo
		// -- Visitar cidades vizinhas (Loop)
		// -- Comparar o valor do caminho percorrido, com a estimativa atual da cidade destino.
		// -- Se for menor, atualizar a estimativa.
		// -- Volta para o 2° passo

		City v0 = new City("v0", 0, false, null);
		City v1 = new City("v1", 0, false, null);
		City v2 = new City("v2", 0, false, null);

		ArrayList<City> cities = new ArrayList<City>();
		cities.add(v0);
		cities.add(v1);
		cities.add(v2);
		ArrayList<Route> routes = new ArrayList<Route>();
		Route rota1 = new Route(10, v0, v1);
		Route rota2 = new Route(50, v0, v2);
		routes.add(rota1);
		routes.add(rota2);

		initializeEstimates(cities, v0);
	}

	/**
	 * <p>
	 * 1° Passo: Inicialização do algoritmo de Dijstra.
	 * Inicilializa a estimativa da primeira cidade com zero.
	 * As demais cidades terão a estimativa inicializada como infinito.
	 *
	 * @param cities Cidades que representa o map a ser percorrido
	 * @param from Cidade de partida para o cálculo da menor distancia.
	 */
	private void initializeEstimates( ArrayList<City> cities, City from ) {
		from.setAsFirstCity();
		for (City city: cities) {
			// Exclui a primeira cidade, nas demais marcar a distancia inicial como infinito.
			if ( !city.getName().equals( from.getName()) ) {
				city.initialize();
			}
		}
	}
}
