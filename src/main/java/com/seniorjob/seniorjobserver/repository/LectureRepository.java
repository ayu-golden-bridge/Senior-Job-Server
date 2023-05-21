package com.seniorjob.seniorjobserver.repository;

import com.seniorjob.seniorjobserver.controller.LectureController;
import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import com.seniorjob.seniorjobserver.dto.LectureDto;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
    List<LectureEntity> findByTitleContaining(String title);
}