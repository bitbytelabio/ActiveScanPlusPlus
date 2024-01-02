package io.bitbytelab.activescanpp;

import burp.*;

public class ActiveScanPlusPlus implements IBurpExtender {
    private static String VERSION = "0.0.1";
    private IExtensionHelpers helpers;
    private IBurpExtenderCallbacks callbacks;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
        callbacks.setExtensionName("ActiveScan++");
        boolean collab_enabled = true;
        if (callbacks.saveConfigAsJson("project_options.misc.collaborator_server").contains("\"type\":\"none\"")) {
            collab_enabled = false;
            System.out.println("Collaborator not enabled; skipping checks that require it");
        }

        System.out.println(String.format("Successfully loaded activeScan++ v%s", VERSION));
        return;
    }
}