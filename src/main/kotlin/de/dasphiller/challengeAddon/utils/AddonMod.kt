package de.dasphiller.challengeAddon.utils

import de.dasphiller.challengeAddon.AddonManager
import de.miraculixx.challenge.api.modules.challenges.ChallengeTags
import de.miraculixx.challenge.api.modules.challenges.CustomChallengeData
import de.miraculixx.challenge.api.settings.ChallengeData
import de.miraculixx.challenge.api.utils.Icon
import de.miraculixx.challenge.api.utils.IconNaming
import de.dasphiller.challengeAddon.MAddon
import de.dasphiller.challengeAddon.mods.DamageRandomEffect
import de.miraculixx.challenge.api.settings.ChallengeBoolSetting
import java.util.*

/**
 * All of our addon mods. Each mod is unique by his [uuid] but not bound to it on each startup. It is only important that the [uuid] is the same at all time of one session.
 * @param uuid The unique ID. Don't choose a simple hard coded [uuid], it could conflict with other addons
 */
enum class AddonMod(val uuid: UUID) {
    DAMAGE_RANDOM_EFFECT(UUID.randomUUID())
    ;

    /**
     * Holds all mod data. Should only be called once at startup to ship all data to the MUtils API
     * @see AddonManager.loadMods
     */
    fun getModData(): CustomChallengeData {
        return when (this) {
            DAMAGE_RANDOM_EFFECT -> CustomChallengeData(
                uuid,
                DamageRandomEffect(),
                AddonManager.getSettings(this),
                Icon(
                    "IRON_PICKAXE",
                    naming = IconNaming(
                        cmp("Damage Random Effect"),
                        listOf(cmp("If you take damage you get a random effect"), cmp("randomizer"))
                    )
                ),
                setOf(ChallengeTags.RANDOMIZER, ChallengeTags.ADDON),
                MAddon.addonName
            )
        }
    }

    /**
     * Holds all settings information. Should only be called on initial startup if no saved settings are present.
     *
     * @see AddonManager.getSettings
     */
    fun getDefaultSetting(): ChallengeData {
        return when (this) {
         //DamageRandomEffect
            DAMAGE_RANDOM_EFFECT -> ChallengeData(
                mapOf(
                    "effectLevel" to ChallengeBoolSetting("COMMAND_BLOCK", true)
                ),
                mapOf(
                    "effectLevel" to IconNaming(
                        cmp("Add Levels"),
                        listOf(cmp("If you take damage and get the same effect should the level of the effect add up"))
                    )
                )
            )
        }
    }
}