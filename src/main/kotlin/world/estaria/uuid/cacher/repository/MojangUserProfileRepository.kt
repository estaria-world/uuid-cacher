package world.estaria.uuid.cacher.repository

import world.avionik.database.simplified.repository.AbstractJedisRepository
import world.estaria.uuid.cacher.user.MojangUserProfile
import java.util.UUID

/**
 * @author Niklas Nieberler
 */

class MojangUserProfileRepository : AbstractJedisRepository<MojangUserProfile>(
    MojangUserProfile::class.java,
    "mojang_user_profiles"
) {

    fun insertUserProfile(mojangUserProfile: MojangUserProfile) {
        insert(mojangUserProfile.getUUID().toString(), mojangUserProfile)
    }

    fun getMojangUserProfile(username: String): MojangUserProfile? {
        return findAll().firstOrNull { it.name == username }
    }

    fun getMojangUserProfile(uniqueId: UUID): MojangUserProfile? {
        return find(uniqueId.toString())
    }

}