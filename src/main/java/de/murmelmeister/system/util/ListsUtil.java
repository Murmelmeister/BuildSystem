package de.murmelmeister.system.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class ListsUtil {
    private Set<UUID> buildMode;

    public void register() {
        this.buildMode = new HashSet<>();
    }

    public Set<UUID> getBuildMode() {
        return buildMode;
    }
}
