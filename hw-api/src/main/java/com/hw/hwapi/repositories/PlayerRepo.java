package com.hw.hwapi.repositories;

import com.hw.hwapi.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends CrudRepository<Player, Long> {

    List<Player> findAllPlayersByTeamId(Long teamId);


}
