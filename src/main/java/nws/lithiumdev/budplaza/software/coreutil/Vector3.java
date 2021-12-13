package nws.lithiumdev.budplaza.software.coreutil;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a world/space-independent location consisted of X, Y and Z components.
 */
public class Vector3 {
    /**
     * Gets or sets the X component of this instance.
     */
    public double x;
    /**
     * Gets or sets the Y component of this instance.
     */
    public double y;
    /**
     * Gets or sets the Z component of this instance.
     */
    public double z;

    /**
     * Initializes a new instance of the {@link Vector3} class.
     * @param x The X component.
     * @param y The Y component.
     * @param z The Z component.
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @NotNull
    public Location toBukkitLocation(@NotNull World world) {
        return new Location(world, x, y, z);
    }
}
