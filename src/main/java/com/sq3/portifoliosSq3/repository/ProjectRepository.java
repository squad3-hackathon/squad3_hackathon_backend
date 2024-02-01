package com.sq3.portifoliosSq3.repository;

import com.sq3.portifoliosSq3.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Busca projetos pelos nomes das tags
    @Query("SELECT p FROM Project p JOIN p.tags t WHERE t.name IN :tags")
    List<Project> findByTags(@Param("tags") List<String> tags);

    // Busca projetos pelos nomes das tags de um usuario
    @Query("SELECT p FROM Project p JOIN p.tags t WHERE p.user.id = :userId AND t.name IN :tags")
    List<Project> findByUserIdAndTags(@Param("userId") Long userId, @Param("tags") List<String> tags);
}
