package com.algorithm;

import com.algorithm.entity.City;
import com.algorithm.entity.Route;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

/** REQUSITOS */
// ---------------------------------------------------------------------------------------

/** Inicialização */
// 1° Passo:
// -- Inicilializa a estimativa da primeira cidade com zero.
// -- As demais cidades terão a estimativa inicializada como infinito.
// -- Colocar todas as cidades como aberta

// 2° Passo: (Loop)
// -- Enquanto tiver cidade aberta,
// -- Buscar a cidade com menor estimativa. OBS: entre as cidades abertas.
// -- Se encontrar fecha a cidade.

// 3° Passo
// -- Visitar cidades vizinhas (Loop)
// -- Comparar o valor do caminho percorrido, com a estimativa atual da cidade destino.
// -- Se for menor, atualizar a estimativa.
// -- Volta para o 2° passo

@SpringBootApplication
public class DijkstraAlgorithmApplication implements CommandLineRunner {

	ArrayList<City> cities = new ArrayList<City>();

	public static void main(String[] args) {
		SpringApplication.run(DijkstraAlgorithmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// TODO: Saparar esse código abaixo, em alguma estrutura para carregar as rotas.
		City v0 = new City("v0", 0, false, null);
		City v1 = new City("v1", 0, false, null);
		City v2 = new City("v2", 0, false, null);
		City v3 = new City("v3", 0, false, null);
		City v4 = new City("v4", 0, false, null);
		City v5 = new City("v4", 0, false, null);

		cities.add(v0);
		cities.add(v1);
		cities.add(v2);
		cities.add(v3);
		cities.add(v4);
		cities.add(v5);

		ArrayList<Route> routes = new ArrayList<Route>();
		Route rota1 = new Route(10, v0, v1);
		Route rota2 = new Route(5, v0, v2);
		Route rota3 = new Route(1, v1, v3);
		Route rota4 = new Route(3, v2, v1);
		Route rota5 = new Route(8, v2, v3);
		Route rota6 = new Route(2, v2, v4);
		Route rota7 = new Route(4, v3, v4);
		Route rota8 = new Route(4, v3, v5);
		Route rota9 = new Route(6, v4, v5);
		routes.add(rota1);
		routes.add(rota2);
		routes.add(rota3);
		routes.add(rota4);
		routes.add(rota5);
		routes.add(rota6);
		routes.add(rota7);
		routes.add(rota8);
		routes.add(rota9);

		// 1° Passo:
		initializeEstimates(v0);
		// 2° Passo: (Loop)
		// -- Enquanto tiver cidade aberta,
		while (hasOpenedCity(cities)) {
			// -- Buscar a cidade com menor estimativa. OBS: entre as cidades abertas.
			City city = getLowestEstimate(cities);
			// -- Se encontrar fecha a cidade.
			if (city == null)
				break;

			// Fecha a cidade
			city.setOpen(false);
			// 3° Passo
			relaxCity(city, routes);
		}
		// TODO: Exibir a melhor rota...
		// TODO: Qual a rota destino?
		// TODO: Imprimir o caminho do destino para a origem!
		System.out.println("Finalizou o algoritmo!");
	}


	// 3° Passo
	// -- Visitar cidades vizinhas (Loop)
	// -- Comparar o valor do caminho percorrido, com a estimativa atual da cidade destino.
	// -- Se for menor, atualizar a estimativa.
	// -- Volta para o 2° passo

	/**
	 * <p>
	 * Relaxar a cidade, significa percorrer todos os seus vizinhos,
	 * e para cada vizinho encontrado, verificar se o valor atual da
	 * sua estimativa é menor que o caminho percorrido até o mesmo,
	 * se for menor, atualizar o valor da estimativa, e atualizar
	 * a cidade de onde veio.
	 * @param from
	 * @param routes
	 */
	// TODO: Fazer uma melhor implementação para evitar percorrer todas as
	// TODO: rotas do mapa, mesmo as que não partem da origem desejada.
	private void relaxCity(City from, ArrayList<Route> routes) {
		for (Route route: routes) {
			// A cidade origem é a mesma informada nos parametros.
			// Queremos apenas visitar as rotas da cidade origem
			if ( from.isEquals(route.getFrom()) ) {
				// Cidade destiInteger distance = route.getDistance();no
				City to = route.getTo();
				// Valor do caminho percorrido
				Integer distance = route.getDistance();
				// Valor da estimativa da cidade origem mais a distancia
				Integer path = from.getEstimate() + distance;
				// O nova caminho é mais curto?
				if ( path <= to.getEstimate() ) {
					to.updateBetterEstimate(from, path);
				}
			}
		}
	}

	/**
	 * <p>
	 * Busca a cidade com menor estimativa, entre as cidades abertas.
	 * @param cities List de cidades disponíveis.
	 * @return A cidade com a menor estimativa, se houver.
	 */
	private City getLowestEstimate(ArrayList<City> cities) {
		// Inicializa a estimativa com infinito.
		Integer lowestEstimate = Integer.MAX_VALUE;
		City lowestEstimateCity = null;
		// Para cada cidade...
		for (City city: cities) {
			// A cidade está aberta? a cidade tem uma menor estimativa?
			if ( city.isOpen() && city.getEstimate() <= lowestEstimate ) {
				// Atualiza a menor estimativa.
				lowestEstimate = city.getEstimate();
				// Salva a cidade com menor estimativa
				lowestEstimateCity = city;
			}
		}
		// Retorna a cidade com menor estimativa.
		return lowestEstimateCity;
	}

	/**
	 * <p>
	 * Verifica se ainda tem cidade aberta.
	 * @param cities Lista de cidades
	 * @return retorna true, se tiver cidade aberta.
	 */
	private boolean hasOpenedCity(ArrayList<City> cities) {
		for (City city: cities) {
			if (city.isOpen())
				return true;
		}
		return false;
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
	private void initializeEstimates(City from ) {
		from.setAsFirstCity();
		for (City city: cities) {
			// Exclui a primeira cidade, nas demais marcar a distancia inicial como infinito.
			if ( !city.getName().equals( from.getName()) ) {
				city.initialize();
			}
		}
	}
}
