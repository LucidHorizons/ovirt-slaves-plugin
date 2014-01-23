package org.jenkinsci.plugins.ovirt;

import hudson.Plugin;
import hudson.util.ListBoxModel;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PluginEntry is the entry point to provide plugin functionality.
 * One instance of this class will be created by Jenkins.
 *
 * @See <a href="http://javadoc.jenkins-ci.org/hudson/Plugin.html">
 */
public class PluginEntry extends Plugin {

    private static final java.util.logging.Logger LOGGER = Logger.getLogger(PluginEntry.class.getName());

    @Override
    public void start() throws Exception {
        LOGGER.log(Level.FINE, "Starting ovirt-slave plugin");
    }

    @Override
    public void stop() throws Exception {
        LOGGER.log(Level.FINE, "Stopping ovirt-slave plugin");
    }


    public ListBoxModel doTestValues(@QueryParameter("value") String value) throws IOException, ServletException {
        ListBoxModel m = new ListBoxModel();

        for(int i = 0; i < 5; i++) {
            m.add((String) value + i, (String) value + i);
        }

        // make the third option selected initially
        m.get(3).selected = true;
        return m;
    }
}
