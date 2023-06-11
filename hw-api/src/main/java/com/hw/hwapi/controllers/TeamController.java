package com.hw.hwapi.controllers;

import com.hw.hwapi.models.Team;
import com.hw.hwapi.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class TeamController {
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    @Autowired
    private TeamService teamService;




    //endpoint to create a teams
    @PostMapping("/teams")
    public ResponseEntity<Void> createTeam(@RequestBody @Valid Team team){

        teamService.addTeam(team);
        logger.info("Team created successfully");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Void> updateTeam(@PathVariable Long id, @RequestBody @Valid Team team){
        teamService.updateTeam(id, team);
        logger.info("Team has been updated");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // get a teams by id
    @GetMapping("/teams/{id}")
    public Optional<Team> getTeamById(@PathVariable Long id){
        return teamService.getTeamById(id);
    }


    @GetMapping("/teams")
    public ResponseEntity<?> getAllTeams (){

        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/team")  //search functionality that searches a team by its name
    public ResponseEntity<Team> getAllOrGetTeamByName(@RequestParam(value = "name") String name) {
        return (new ResponseEntity<>(this.teamService.getTeamByName(name), HttpStatus.OK));

    }


    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        teamService.deleteTeam(id);
        logger.info("Team has been deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
