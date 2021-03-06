package za.co.entelect.challenge.game.contracts.renderer

import za.co.entelect.challenge.game.contracts.Config.Config
import za.co.entelect.challenge.game.contracts.game.CarGamePlayer
import za.co.entelect.challenge.game.contracts.map.CarGameMap

class CsvRenderer extends BaseMapRenderer {

    override def renderFragment(gameMap: CarGameMap, gamePlayer: CarGamePlayer): String = {
        val mapFragment = gameMap.getMapFragment(gamePlayer);
        val csvHeaderString = "Round,PlayerId,Position:X,Position:Y,Speed,State,Boosting,Boost-Counter,#Boosts,#Oil,Score";

        val currentRound = mapFragment.getCurrentRound();
        val player = mapFragment.getPlayer();
        val playerInfoString =
            currentRound + "," +
            player.getId() + "," +
            player.getPosition().getLane() + "," +
            player.getPosition().getBlockNumber() + "," +
            player.getSpeed() + "," +
            player.getState() + "," +
            player.isBoosting() + "," +
            player.getBoostCounter() + "," +
            player.getPowerups().count(x => x == Config.BOOST_POWERUP_ITEM) + "," +
            player.getPowerups().count(x => x == Config.OIL_POWERUP_ITEM) + "," +
            player.getScore();

        return playerInfoString;
    }

    override def renderVisualiserMap(gameMap: CarGameMap) : String = {
        throw new NotImplementedError("Csv renderer render visualiser map");
    }
}
