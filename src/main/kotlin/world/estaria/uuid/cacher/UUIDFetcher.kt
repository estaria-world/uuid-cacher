package world.estaria.uuid.cacher

import kotlinx.serialization.json.Json
import world.estaria.uuid.cacher.enums.FetcherType
import world.estaria.uuid.cacher.http.HttpFetcher
import world.estaria.uuid.cacher.user.MojangUserProfile

/**
 * @author Niklas Nieberler
 */

/**
 * Don't use the fetcher too often because nothing is cached in this class.
 * If you send too many requests to the Mojang API, you may be blocked for a certain period of time!
 *
 * Therefore, it is better to use the [UUIDCacher], which caches your [MojangUserProfile]
 */
object UUIDFetcher {

    /**
     * Gets the [MojangUserProfile] class by finding out whether the profile is an uuid or not
     * @param profile name or uuid of user
     * @return the [MojangUserProfile] from the user
     */
    fun get(profile: Any): MojangUserProfile? {
        val fetcherType = FetcherType.getFetcherType(profile)
        return get(fetcherType, profile)
    }

    /**
     * Gets the [MojangUserProfile] class of the matching [FetcherType]
     * @param fetcherType to find out the [MojangUserProfile]
     * @param profile name or uuid of user
     * @return the [MojangUserProfile] from the user
     */
    fun get(fetcherType: FetcherType, profile: Any): MojangUserProfile? {
        val stringFromUrl = HttpFetcher.getStringFromUrl(fetcherType.get(profile)) ?: return null
        return Json.decodeFromString<MojangUserProfile>(stringFromUrl)
    }

}