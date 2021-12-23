package nws.lithiumdev.budplaza.software.coreutil;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a world/space-independent location consisted of X, Y and Z components.
 */
public class Vector3 {
    private double x;
    private double y;
    private double z;

    /**
     * Initializes a new instance of the {@link Vector3} class.
     * @param x The X component.
     * @param y The Y component.
     * @param z The Z component.
     */
    public Vector3(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    @NotNull
    public Location toBukkitLocation(@NotNull World world) {
        return new Location(world, getX(), getY(), getZ());
    }

    /**
     * Gets or sets the X component of this instance.
     */
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets or sets the Y component of this instance.
     */
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets or sets the Z component of this instance.
     */
    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
