package cc.synkdev.items;

import org.bukkit.event.Event;

public interface ClickActions<T extends Event> {
    void execute(T var1);
}
