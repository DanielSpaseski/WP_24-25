package mk.ukim.finki.wp.kol2023.g1.service.impl;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.model.Team;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidPlayerIdException;
import mk.ukim.finki.wp.kol2023.g1.repository.PlayerRepository;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerService;
import mk.ukim.finki.wp.kol2023.g1.service.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.kol2023.g1.service.FieldFilterSpecification.*;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamService teamService){
        this.playerRepository=playerRepository;
        this.teamService=teamService;
    }
    @Override
    public List<Player> listAllPlayers() {
        return this.playerRepository.findAll();
    }

    @Override
    public Player findById(Long id) {
        return this.playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
    }

    @Override
    public Player create(String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        Team team1 = this.teamService.findById(team);
        Player player = new Player(name,bio,pointsPerGame,position,team1);
        return this.playerRepository.save(player);
    }

    @Override
    public Player update(Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        Team team1 = this.teamService.findById(team);
        Player player = this.playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        player.setName(name);
        player.setBio(bio);
        player.setPointsPerGame(pointsPerGame);
        player.setPosition(position);
        player.setTeam(team1);
        return this.playerRepository.save(player);
    }

    @Override
    public Player delete(Long id) {
        Player player = this.playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        this.playerRepository.delete(player);
        return player;
    }

    @Override
    public Player vote(Long id) {
        Player player = this.playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        player.setVotes(player.getVotes() + 1);
        return null;
    }

    @Override
    public Page<Player> findPage(Double pointsPerGame, PlayerPosition position, Integer pageNum, Integer pageSize) {
        Specification<Player> specification = Specification
                .where(greaterThan(Player.class, "pointsPerGame", pointsPerGame))
                .and(filterEqualsV(Player.class, "position", position));

        return this.playerRepository.findAll(
                specification,
                PageRequest.of(pageNum - 1, pageSize)
        );

    }
}
