package br.edu.ufcg.computacao.si1.repositories;

import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("select a from Advertisement a where type = ?1")
    List<Advertisement> findByType(String typeValue);

}