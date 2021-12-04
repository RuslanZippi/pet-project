package main.repository;


import main.model.GlobalSetting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalSettingRep extends CrudRepository<GlobalSetting, Long> {
    GlobalSetting findByCode(String code);
}
