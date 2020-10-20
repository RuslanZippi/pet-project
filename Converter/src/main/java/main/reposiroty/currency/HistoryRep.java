package main.reposiroty.currency;

import main.base.currency.HistoryBase;
import main.base.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRep  extends CrudRepository<HistoryBase, Long> {
    List<HistoryBase> findAllByUser(User user);
}
