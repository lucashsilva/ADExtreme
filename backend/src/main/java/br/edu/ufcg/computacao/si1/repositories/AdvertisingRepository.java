package br.edu.ufcg.computacao.si1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.si1.models.Advertising;


public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {

}