package world.estaria.uuid.cacher.user

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
data class MojangUserProfile(
    val id: String,
    val name: String
)