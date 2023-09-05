package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DemandRepository extends JpaRepository<Demand, Integer> {
    //    @Query("select d from demand d where d.course.id=?1")
    List<Demand> findAllByCourseId(UUID id);
}
