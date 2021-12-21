package me.golf.researchjpa.nplusone;

import me.golf.researchjpa.nplusone.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcademyServiceTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AcademyService academyService;

    @AfterEach
    void cleanAll() {
        academyRepository.deleteAll();
        teacherRepository.deleteAll();
    }

    @BeforeEach
    void setup() {
        List<Academy> academies = new ArrayList<>();
        Teacher teacher = teacherRepository.save(new Teacher("선생님"));

        for (int i = 0; i < 10; i++) {
            Academy academy = Academy.builder()
                    .name("강남 스쿨" + i)
                    .build();

            academy.addSubject(Subject.builder().name("자바웹개발" + i).teacher(teacher).build());
            academy.addSubject(Subject.builder().name("파이썬자동화" + i).teacher(teacher).build()); // Subject를 추가
            academies.add(academy);
        }

        academyRepository.saveAll(academies);
    }

    @Test
    @DisplayName("여러개를_조회시_Subject가_N1_쿼리가발생한다")
    void academyN1Yes() {
        // given
        List<String> subjectNames = academyService.findAllSubjectNames();

        // then
        assertThat(subjectNames.size()).isEqualTo(10);
    }

    @Test
    void academyFetch() {
        // given
        List<Academy> academies = academyRepository.findAllJoinFetch();
        List<String> subjectNames = academyService.findAllSubjectNamesByJoinFetch();

        // then
        assertThat(academies.size()).isEqualTo(20);
        assertThat(subjectNames.size()).isEqualTo(20);
    }

    @Test
    void academyDistinct() {
        // given
        System.out.println("조회 시작");
        List<Academy> academies = academyRepository.findAllJoinFetchDistinct();

        // then
        System.out.println("조회 끝");
        assertThat(academies.size()).isEqualTo(10);
    }

    @Test
    void academyAll() {
        //given
        System.out.println("조회 시작");
        List<Teacher> teachers = academyRepository.findAllWithTeacher().stream()
                .map(a -> a.getSubjects().get(0).getTeacher())
                .collect(Collectors.toList());

        //then
        System.out.println("조회 끝!");
        assertThat(teachers.size()).isEqualTo(10);
    }

    @Test
    void academyAllEntityGraph() {
        //given
        System.out.println("조회 시작");
        List<Teacher> teachers = academyRepository.findAllEntityGraphWithTeacer().stream()
                .map(a -> a.getSubjects().get(0).getTeacher())
                .collect(Collectors.toList());

        //then
        System.out.println("조회 끝");
        assertThat(teachers.size()).isEqualTo(10);
        assertThat(teachers.get(0).getName()).isEqualTo("선생님");
    }
}