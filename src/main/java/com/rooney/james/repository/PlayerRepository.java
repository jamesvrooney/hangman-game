package com.rooney.james.repository;

import com.rooney.james.model.Game;
import com.rooney.james.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jamesvrooney on 22/03/17.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

}