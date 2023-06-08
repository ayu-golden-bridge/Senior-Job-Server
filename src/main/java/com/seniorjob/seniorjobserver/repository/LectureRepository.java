package com.seniorjob.seniorjobserver.repository;

import com.seniorjob.seniorjobserver.domain.entity.LectureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {

    List<LectureEntity> findAllByOrderByCreatedDateDesc();

    List<LectureEntity> findAllByOrderByCreatedDateAsc();

    List<LectureEntity> findAllByOrderByMaxParticipantsDesc();

    List<LectureEntity> findAllByOrderByMaxParticipantsAsc();

    List<LectureEntity> findAllByOrderByPriceDesc();

    List<LectureEntity> findAllByOrderByPriceAsc();

    Page<LectureEntity> findAll(Pageable pageable);

    List<LectureEntity> findByTitleContaining(String title);

    List<LectureEntity> findByTitleContainingAndStatus(String title, LectureEntity.LectureStatus status);

    List<LectureEntity> findByStatus(LectureEntity.LectureStatus status);

}
