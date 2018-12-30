package info.stasha.selenx.tags;

import java.util.Set;

/**
 *
 * @author stasha
 */
public abstract class Root extends Id {

    private UniqueSet<PageObject> pageObjects = new UniqueSet<>();
    private UniqueSet<Element> elements = new UniqueSet<>();

    public UniqueSet<PageObject> getPageObjects() {
        return pageObjects;
    }

    public void setPageObjects(UniqueSet<PageObject> pageObjects) {
        this.pageObjects = pageObjects;
    }

    public UniqueSet<Element> getElements() {
        return elements;
    }

    public void setElements(UniqueSet<Element> elements) {
        this.elements = elements;
    }

}
