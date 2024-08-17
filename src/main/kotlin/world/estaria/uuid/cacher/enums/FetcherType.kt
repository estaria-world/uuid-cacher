package world.estaria.uuid.cacher.enums

import world.estaria.uuid.cacher.extension.isUUID
import world.estaria.uuid.cacher.user.MojangUserProfileUrls

/**
 * @author Niklas Nieberler
 */

enum class FetcherType(
    private val url: String
) {

    UUID(MojangUserProfileUrls.PROFILE_USERNAME),

    NAME(MojangUserProfileUrls.PROFILE_UUID);

    /**
     * Gets the matching url path
     * @param profile name or uuid of user
     * @return url path to fetch
     */
    fun get(profile: Any): String {
        return this.url + profile
    }

    companion object {
        /**
         * See if the profile string is a uuid or just a username
         * @param profile name or uuid of user
         * @return the correct [FetcherType]
         */
        fun getFetcherType(profile: Any): FetcherType {
            return if (profile.toString().isUUID()) NAME else UUID
        }
    }

}