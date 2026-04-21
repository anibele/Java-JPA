package inf.anibele.usuariotelefonerecord.repository;

import inf.anibele.usuariotelefonerecord.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
