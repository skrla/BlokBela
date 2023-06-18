package ma.skrla.blokbela.data.mapper

import ma.skrla.blokbela.data.local.model.PlayerEntity
import ma.skrla.blokbela.data.local.model.ScoreEntity
import ma.skrla.blokbela.data.local.model.TeamEntity
import ma.skrla.blokbela.data.local.model.relations.TeamEntityWithPlayerEntityAndScoreEntity
import ma.skrla.blokbela.domain.Player
import ma.skrla.blokbela.domain.Score
import ma.skrla.blokbela.domain.Team

fun TeamEntityWithPlayerEntityAndScoreEntity.toTeam(trump: Boolean) : Team {
    return Team(
        id = team.id,
        name = team.name,
        players = players.map { it.toPlayer() },
        score = score.map { it.toScore()},
        trump = trump,
        bela = false
    )
}

fun PlayerEntity.toPlayer() : Player {
    return Player(
        id = id,
        name = name,
        dealer = dealer,
        score = null
    )
}

fun ScoreEntity.toScore() : Score {
    return Score(
        id = id,
        round = round,
        points = points
    )
}

fun Team.toTeamEntity() : TeamEntity {
    return TeamEntity(
        id = id,
        name = name
    )
}

fun Score.toScoreEntity(playerId: Int?, teamId: Int?) : ScoreEntity {
    return ScoreEntity(
        id = id,
        round = round,
        points = points,
        playerId = playerId,
        teamId = teamId
    )
}