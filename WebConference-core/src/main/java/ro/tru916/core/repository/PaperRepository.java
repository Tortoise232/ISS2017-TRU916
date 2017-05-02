package ro.tru916.core.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.Paper;

/**
 * Created by cata on 27.04.2017.
 */

public interface PaperRepository extends ConferenceBaseRepository<Paper, Long> {
}
