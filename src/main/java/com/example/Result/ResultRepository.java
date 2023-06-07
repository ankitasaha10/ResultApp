package com.example.Result;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultModel, Integer> {

    ResultModel findByName(String name);
}
