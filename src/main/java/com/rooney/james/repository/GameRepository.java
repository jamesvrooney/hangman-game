package com.rooney.james.repository;

import com.rooney.james.model.Game;
import com.rooney.james.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by jamesvrooney on 22/03/17.
 */
public interface GameRepository extends JpaRepository<Game, Long> {
    Set<Game> findByPlayer(Player player);
}