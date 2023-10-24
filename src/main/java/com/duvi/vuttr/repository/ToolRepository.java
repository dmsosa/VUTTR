package com.duvi.vuttr.repository;

import com.duvi.vuttr.domain.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    public boolean existsByTitle(String title);
}
