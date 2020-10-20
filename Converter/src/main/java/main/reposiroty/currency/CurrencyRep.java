package main.reposiroty.currency;

import main.base.currency.CurrencyBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CurrencyRep extends CrudRepository<CurrencyBase,Long> {
    CurrencyBase findByDate(String date);
    CurrencyBase findByCharCode(String charCode);
    List<CurrencyBase> findAllByOrderByIdDesc();
}
