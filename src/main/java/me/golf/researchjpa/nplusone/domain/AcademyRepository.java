package me.golf.researchjpa.nplusone.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcademyRepository extends JpaRepository<Academy, Long> {

    /**
     * 1. join fetch를 통한 조회
     */
    @Query("select a from Academy a join fetch a.subjects")
    List<Academy> findAllJoinFetch();

    /**
     * 2. @EntityGraph
     */
    @EntityGraph(attributePaths = "subjects")
    @Query("select a from Academy a")
    List<Academy> findAllEntityGraph();

    /**
     * 3. join fetch + distinct를 통한 조회
     */
    @Query("select DISTINCT a from Academy a join fetch a.subjects")
    List<Academy> findAllJoinFetchDistinct();

    /**
     * 4. @EntityGraph + distinct사용
     */
    @EntityGraph(attributePaths = "subjects")
    @Query("select DISTINCT a from Academy a")
    List<Academy> findAllEntityGraphDistinct();

    /**
     * 5. Academy + subject + teacher를 join fetch로 조회
     */
    @Query("select DISTINCT a from Academy a join fetch a.subjects s join fetch s.teacher")
    List<Academy> findAllWithTeacher();

    /**
     * 6. Academy + subject + teacher를 join fetch로 조회 + @EntityGraph
     */
    @EntityGraph(attributePaths = {"subjects", "subjects.teacher"})
    @Query("select DISTINCT a from Academy a")
    List<Academy> findAllEntityGraphWithTeacer();
}
