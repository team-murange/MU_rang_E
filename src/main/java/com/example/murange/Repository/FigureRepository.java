package com.example.murange.Repository;

import com.example.murange.Domain.Figure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureRepository extends JpaRepository<Figure, Long> {
}
