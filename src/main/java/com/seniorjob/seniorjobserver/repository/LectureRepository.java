package com.seniorjob.seniorjobserver.repository;

import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
    List<LectureEntity> findByTitleContaining(String title);
}