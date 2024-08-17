package world.estaria.uuid.cacher

import world.estaria.uuid.cacher.extension.isUUID
import world.estaria.uuid.cacher.extension.toUUID
import world.estaria.uuid.cacher.repository.MojangUserProfileRepository
import world.estaria.uuid.cacher.user.MojangUserProfile
import java.util.UUID

/**
 * @author Niklas Nieberler
 */

object UUIDCacher {

    private val repository = MojangUserProfileRepository()

    /**
     * Gets the cached name from the uniqueId
     * @param uniqueId from whom you want the name
     * @return the username from the uniqueId
     */
    fun getName(uniqueId: UUID): String {
        return get(uniqueId).name
    }

    /**
     * Gets the cached uniqueId from the username
     * @param username from whom you want the uniqueId
     * @return the uniqueId from the username
     */
    fun getUUID(username: String): UUID {
        return get(username).getUUID()
    }

    /**
     * Gets the cached [MojangUserProfile] class by finding out whether the profile is an uuid or not
     * @param profile name or uuid of user
     * @return the [MojangUserProfile] from the user
     */
    fun get(profile: Any): MojangUserProfile {
        return getFromDatabase(profile) ?: saveToDatabase(profile)
    }

    private fun getFromDatabase(profile: Any): MojangUserProfile? {
        val profileString = profile.toString()
        return if (profileString.isUUID()) {
            this.repository.getMojangUserProfile(profileString.toUUID())
        } else {
            this.repository.getMojangUserProfile(profileString)
        }
    }

    private fun saveToDatabase(profile: Any): MojangUserProfile {
        val mojangUserProfile = UUIDFetcher.get(profile)
            ?: throw NullPointerException("failed to find MojangUserProfile $profile")
        this.repository.insertUserProfile(mojangUserProfile)
        return mojangUserProfile
    }

}