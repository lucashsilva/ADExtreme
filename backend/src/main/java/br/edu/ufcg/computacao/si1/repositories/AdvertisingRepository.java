package br.edu.ufcg.computacao.si1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.si1.models.advertising.Advertising;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {

    @Query("select a from Advertising a where type = ?1")
    List<Advertising> findByType(String typeValue);

}