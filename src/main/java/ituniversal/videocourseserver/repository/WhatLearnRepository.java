package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.WhatLearn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WhatLearnRepository extends JpaRepository<WhatLearn, Integer> {
//    @Query("select w from what_learn w where w.course.id=?1")
    List<WhatLearn> findAllByCourseId(UUID id);
}
