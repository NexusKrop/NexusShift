// (C) BudPlaza 2021, 2022 All rights reserved.
// Licensed under GNU Affero GPL, either V3 or any later version.

package nws.lithiumdev.budplaza.software.mod.commands.framework;

import nws.lithiumdev.budplaza.software.mod.commands.definitions.ICommand;

public interface OOCommand extends ICommand {
    String getName();
    OOCommand addSubcommand(OOCommand command);
}
