package com.example.murange.Repository;

import com.example.murange.Domain.Emotion;
import com.example.murange.Domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
