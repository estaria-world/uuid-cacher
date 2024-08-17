package world.estaria.uuid.cacher.user

import kotlinx.serialization.Serializable
import world.estaria.uuid.cacher.extension.toUUID
import java.util.*

/**
 * @author Niklas Nieberler
 */

@Serializable
data class MojangUserProfile(
    val id: String,
    val name: String
) {

    fun getUUID(): UUID {
        return this.id.toUUID()
    }

}