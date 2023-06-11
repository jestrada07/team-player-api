package com.hw.hwapi.repositories;


import com.hw.hwapi.models.Player;
import com.hw.hwapi.models.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends CrudRepository <Team, Long>  {

    Team findByName(String name);


}
