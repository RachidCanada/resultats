package com.example.resultats.repository;

import com.example.resultats.model.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultatRepository extends JpaRepository<Resultat, Long> {
}
