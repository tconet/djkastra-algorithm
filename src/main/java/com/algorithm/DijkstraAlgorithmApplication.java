package com.algorithm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.algorithm.implementation.Dijkstra;

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

	public static void main(String[] args) {
		SpringApplication.run(DijkstraAlgorithmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Dijkstra algorithm = new Dijkstra();
		algorithm.execute("v0","v3");
	}

}
