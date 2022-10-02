package com.example.murange.Repository;

import com.example.murange.Domain.Like;
import com.example.murange.Domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom {


}
