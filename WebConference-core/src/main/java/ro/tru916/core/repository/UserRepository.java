package ro.tru916.core.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.User;

/**
 * Created by cata on 27.04.2017.
 */
public interface UserRepository extends ConferenceBaseRepository<User, Long> {
}
