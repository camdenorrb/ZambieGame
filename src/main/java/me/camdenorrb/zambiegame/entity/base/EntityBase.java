package me.camdenorrb.zambiegame.entity.base;

import me.camdenorrb.zambiegame.type.*;

import java.util.UUID;

/**
 * The basics for an Entity
 */
public interface EntityBase extends Named, Mobile, Ranged, Scaled, Pieced, Tickable, Positioned, Damagable, Spawnable, Teleportable {}