package com.hw.hwapi.services;

import com.hw.hwapi.exceptions.ResourceNotFoundException;
import com.hw.hwapi.models.Player;
import com.hw.hwapi.models.Team;
import com.hw.hwapi.repositories.PlayerRepo;
import com.hw.hwapi.repositories.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private TeamRepo teamRepo;



    protected void verifyPlayerExists(Long idOfPlayer) throws ResourceNotFoundException {
        if (!(this.playerRepo.existsById(idOfPlayer))) {
            throw (new ResourceNotFoundException("Player with ID " + idOfPlayer + " doesn't exist"));
        }
    }




    public void addPlayer(Long teamId, Player player){
        //we need to find the team by id
        Team team = teamRepo.findById(teamId).orElse(null);
        //setting the player as teams new member
        player.setTeam(team);
        //save the player for team with specific id
        playerRepo.save(player);

    }

    public List<Player> getAllOrdersByCustomerId(Long teamId){
        return playerRepo.findAllPlayersByTeamId(teamId);
    }

    public void updatePlayer(Long teamId, Long playerId, Player player){
        Team team = teamRepo.findById(teamId).orElse(null);
        Player p = playerRepo.findById(playerId).orElse(null);
        if( p != null){ // checks if player is there or not - if it exists
            p.setName(player.getName());
            p.setRating(player.getRating());
        }
        player.setTeam(team);
        playerRepo.save(player);

    }


    public void deletePlayer(Long playerId ){
        playerRepo.deleteById(playerId);
    }

    public Player getPlayerById(Long id){
        verifyPlayerExists(id);
        return playerRepo.findById(id).orElse(null);
    }










}
