package com.example.murange.Repository;

import com.example.murange.Domain.Like;
import com.example.murange.Domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {


}
