package game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class GameStats {

    private int numGuesses;

    private boolean isRight;

}
