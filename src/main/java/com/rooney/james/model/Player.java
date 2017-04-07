package com.rooney.james.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Entity
@Table(name = "player")
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator="SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name="SEQUENCE_GENERATOR",sequenceName="player_seq", allocationSize=100)
    private Long id;
    private String username;
    private String password;

    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonManagedReference
    Set<Game> games = new HashSet<>();

    @Override
    public String toString() {
        String result = String.format(
                "Player[id=%d, username='%s']%n",
                id, username);
        if (games != null) {
            for(Game game : games) {
                result += String.format(
                        "Game[id=%d, word='%s']%n",
                        game.getId(), game.getWord());
            }
        }

        return result;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(username);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Player)) {
            return false;
        }

        Player that = (Player) obj;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(username, that.username);

        return eb.isEquals();
    }
}
