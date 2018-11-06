package uteevbkru.repos;

import org.springframework.data.repository.CrudRepository;
import uteevbkru.domain.Message;

public interface MessageRepo extends CrudRepository<Message, Long> {
}
