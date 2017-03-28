package br.edu.ufcg.computacao.si1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("select a from Advertisement a where type = ?1")
    List<Advertisement> findByType(String typeValue);

}