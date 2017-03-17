package br.edu.ufcg.computacao.si1.repositories;

import br.edu.ufcg.computacao.si1.models.advertising.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("select a from Advertising a where type = ?1")
    List<Advertisement> findByType(String typeValue);

}