package com.example.murange.Service;

import com.example.murange.Repository.FigureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FigureService {

    private final FigureRepository figureRepository;



}
