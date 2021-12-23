package nws.lithiumdev.budplaza.software.mod.commands.definitions;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.FloatArgument;
import nws.lithiumdev.budplaza.software.mod.Globals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;

public class FireballCommand implements ICommand {

    @Override
    public void register() {
        new CommandAPICommand("fireball")
                .withArguments(new FloatArgument("power"))
                .withPermission("budplaza.commands.fireball")
                .executesEntity(((sender, args) -> {
                    var entity = sender.getWorld().spawnEntity(sender.getLocation(), EntityType.FIREBALL);
                    if (!(entity instanceof Fireball fireball)) {
                        Globals.logger.error("Failed to spawn fireball: Fireball cannot be casted");
                        return;
                    }

                    fireball.setDirection(sender.getFacing().getDirection());
                    fireball.setVisualFire(true);
                    fireball.setVelocity(sender.getFacing().getDirection());
                }))
                .register();
    }
}
