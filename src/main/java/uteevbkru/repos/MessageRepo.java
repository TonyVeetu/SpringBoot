package uteevbkru.repos;

import org.springframework.data.repository.CrudRepository;
import uteevbkru.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
