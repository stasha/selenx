package info.stasha.selenx.tags;

import com.thoughtworks.xstream.XStream;
import info.stasha.selenx.XmlParser;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author stasha
 */
public class Site {

    static {
        System.setProperty("webdriver.gecko.driver", "/home/stasha/software/selenium-derivers/geckodriver_0.21.0");
    }

    private static Set<Import> imports = new HashSet<>();
    private static UniqueSet<Layout> layouts = new UniqueSet<>();
    private static UniqueSet<Template> templates = new UniqueSet<>();
    private static UniqueSet<Page> pages = new UniqueSet<>();
    private static Site site;

    private String baseUri;

    public static String getBaseUri() {
        return site.baseUri;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        Site.site = site;
    }

    public static Site init(String pathToSiteXml) {
        XStream xstr = XmlParser.getParser();

        String file = pathToSiteXml + "site.xml";
        Site site = null;
        try {
            String str = FileUtils.readFileToString(new File(file), "UTF-8");

            site = (Site) xstr.fromXML(str);
            site.setSite(site);

            Iterator<Import> i = imports.iterator();
            while (i.hasNext()) {
                file = pathToSiteXml + i.next().getFile();
                str = FileUtils.readFileToString(new File(file), "UTF-8");
                xstr.fromXML(str);
            }
        } catch (IOException ex) {
            throw new RuntimeException("File " + file + " not found!");
        }

        Iterator<Layout> it = layouts.iterator();
        while (it.hasNext()) {
            Layout l = it.next();
            if (l.getExtends() != null) {
                Layout ex = (Layout) new XStream().fromXML(new XStream().toXML(layouts.getById(l.getExtends())));
                l.getPageObjects().addAll(ex.getPageObjects());
                l.getElements().addAll(ex.getElements());
            }
        }

        Iterator<Page> pi = pages.iterator();
        while (pi.hasNext()) {
            Page l = pi.next();
            if (l.getExtends() != null) {
                Page ex = (Page) new XStream().fromXML(new XStream().toXML(pages.getById(l.getExtends())));
                l.getPageObjects().addAll(ex.getPageObjects());
                l.getElements().addAll(ex.getElements());
            }

            if (l.getLayout() != null) {
                Layout ex = (Layout) new XStream().fromXML(new XStream().toXML(layouts.getById(l.getLayout())));
                l.getPageObjects().addAll(ex.getPageObjects());
                l.getElements().addAll(ex.getElements());
            }
        }

        pi = pages.iterator();

        while (pi.hasNext()) {
            Page p = pi.next();

            createLinks(p, p, "", "");
        }

        return site;
    }

    private static void createLinks(Page page, Root root, String key, String value) {

        String selector = "";
        String id = "";

        if (!root.getElements().isEmpty()) {
            Iterator<Element> poi = root.getElements().iterator();
            while (poi.hasNext()) {
                Element po = poi.next();
                id = key + " " + po.getId() + " ";
                selector = value + " " + po.getCss() + " ";

                id = id.trim().replaceAll("\\s+", " ");
                selector = selector.trim().replaceAll("\\s+", " ");

                page.getSelectors().put(id, selector);
                System.out.println(page.getId() + " : " + id + " : " + selector);
            }
        }

        if (!root.getPageObjects().isEmpty()) {
            Iterator<PageObject> poi = root.getPageObjects().iterator();
            while (poi.hasNext()) {
                Object obj = poi.next();
                if (obj instanceof PageObject) {
                    PageObject po = (PageObject) obj;
                    if (po.getCss() != null) {
                        key += po.getId() + " ";
                        value += po.getCss() + " ";
                    }

                    createLinks(page, po, key, value);
                }

                key = "";
                value = "";
            }
        }

    }

    public static Set<Import> getImports() {
        return imports;
    }

    public static UniqueSet<Layout> getLayouts() {
        return layouts;
    }

    public static UniqueSet<Template> getTemplates() {
        return templates;
    }

    public static UniqueSet<Page> getPages() {
        return pages;
    }

}
