package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.models.Pie;

import java.util.List;

public interface PieRepository extends CrudRepository<Pie, Long> {
    List<Pie> findByName(String name);
}