package com.hw.hwapi.services;


import com.hw.hwapi.exceptions.NoSuchPropertyException;
import com.hw.hwapi.exceptions.ResourceNotFoundException;
import com.hw.hwapi.models.Player;
import com.hw.hwapi.models.Team;
import com.hw.hwapi.repositories.PlayerRepo;
import com.hw.hwapi.repositories.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {


    @Autowired
    private TeamRepo teamRepo;

    public Team getTeamByName(String name) throws NoSuchPropertyException, ResourceNotFoundException {
        boolean isEmptyString = name.isEmpty();
        if (isEmptyString) {
            throw (new NoSuchPropertyException("No name was provided"));
        } else {
            for (Team team : this.teamRepo.findAll()) {
                if (team.getName().equalsIgnoreCase(name)) {
                    return team;
                }
            }
        }
        throw (new ResourceNotFoundException("Team with name " + name + " not found"));
    }


    protected void checkIfTeamExists(Long idOfTeam) throws ResourceNotFoundException {
        if (!(this.teamRepo.existsById(idOfTeam))) {
            throw (new ResourceNotFoundException("Team ID " + idOfTeam + " not found"));
        }
    }

    //creating a Team
    public void addTeam(Team team){
        teamRepo.save(team);
    }

    //getting all Teams
    public Iterable<Team> getAllTeams(){
        return teamRepo.findAll();
    }

    // getting a team by id
    public Optional<Team> getTeamById(Long id){
        checkIfTeamExists(id);

        return teamRepo.findById(id);
    }
    //updating a team by id
    public void updateTeam(Long id, Team team){
        team.setId(id);
        teamRepo.save(team);
    }

    //deleting a team
    public void deleteTeam(Long id){
        teamRepo.deleteById(id);
    }

    //finding a team by name
    public Team findATeamByName(String name){

        return teamRepo.findByName(name);
    }





}
