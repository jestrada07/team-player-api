package com.hw.hwapi.controllers;

import com.hw.hwapi.models.Player;
import com.hw.hwapi.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
    @Autowired
    private PlayerService playerService;

    @PostMapping("/teams/{teamId}/player")
    public ResponseEntity<Void> createPlayer(@PathVariable Long teamId, @RequestBody @Valid Player player){
        playerService.addPlayer(teamId, player);
        logger.info("Player has been created successfully");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //Get all players by team id
    @GetMapping("/teams/{teamId}/player")
    public ResponseEntity<List<Player>> getAllPlayersByTeamId(@PathVariable Long teamId){
        return new ResponseEntity<>(playerService.getAllOrdersByCustomerId(teamId), HttpStatus.OK);

    }

    //update a player
    @PutMapping("/teams/{teamId}/player/{playerId}")
    public ResponseEntity<Void> updatePlayer(@PathVariable Long teamId, @PathVariable Long playerId, @RequestBody @Valid Player player ){
        playerService.updatePlayer(teamId, playerId, player);
        logger.info("Player updated");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //get a player by ID
    @GetMapping("/player/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);
    }

    //delete an player by ID

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Optional<Player>> removePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        logger.info("Player has been deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
