package world.estaria.uuid.cacher.extension

import java.util.UUID

/**
 * @author Niklas Nieberler
 */

fun String.isUUID(): Boolean {
    return this.length >= 32
}

fun String.toUUID(): UUID {
    val uuidString = this.replace(Regex("(.{8})(.{4})(.{4})(.{4})(.{12})"), "$1-$2-$3-$4-$5")
    return UUID.fromString(uuidString)
}