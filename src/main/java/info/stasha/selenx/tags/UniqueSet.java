package info.stasha.selenx.tags;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;

/**
 *
 * @author stasha
 */
public class UniqueSet<T extends Id> extends LinkedHashSet<T> {

    @Override
    public boolean add(T e) {
        if (this.contains(e)) {
            throw new IllegalArgumentException("Object with id " + e.getId() + " already exists");
        }
        return super.add(e);
    }

    public T getById(String id) {

        return this.stream().filter((T t) -> t.getId().equals(id)).findAny()
                .orElseThrow(() -> {
                    return new NoSuchElementException("There is no element with id: \"" + id + "\"");
                });
    }

}
