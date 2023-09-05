package ituniversal.videocourseserver.repository.rest;

import ituniversal.videocourseserver.entity.FAQ;
import ituniversal.videocourseserver.projection.CustomFAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "list", path = "FAQ", excerptProjection = CustomFAQ.class)
public interface FAQRepository extends JpaRepository<FAQ, Integer> {
}
