package ro.tru916.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by cata on 26.04.2017.
 */
//This interface will be inplemented by every single repository
@NoRepositoryBean
@Transactional
public interface ConferenceRepository<T extends BaseEntity<ID>,ID extends Serializable> extends JpaRepository<T,ID> {

}
