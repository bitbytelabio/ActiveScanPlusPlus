package io.bitbytelab.activescanpp;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import burp.*;

public class PerHostScans implements IScannerCheck {
    private List<String> scanned_hosts;
    private List<List<String>> interestingFileMappings = Arrays.asList(
            Arrays.asList("/.git/config", "[core]", "source code leak?"),
            Arrays.asList("/server-status", "Server uptime", "debug info"),
            Arrays.asList("/.well-known/apple-app-site-association", "applinks",
                    "https://developer.apple.com/library/archive/documentation/General/Conceptual/AppSearch/UniversalLinks.html"),
            Arrays.asList("/.well-known/openid-configuration", "\"authorization_endpoint\"",
                    "https://portswigger.net/research/hidden-oauth-attack-vectors"),
            Arrays.asList("/.well-known/oauth-authorization-server", "\"authorization_endpoint\"",
                    "https://portswigger.net/research/hidden-oauth-attack-vectors"),
            Arrays.asList("/users/confirmation", "onfirmation token",
                    "Websites using the Devise framework often have a race condition enabling email forgery: https://portswigger.net/research/smashing-the-state-machine"));

    @Override
    public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse) {
        return new ArrayList<>();
    }

    @Override
    public List<IScanIssue> doActiveScan(IHttpRequestResponse baseRequestResponse,
            IScannerInsertionPoint insertionPoint) {
        String host = baseRequestResponse.getHttpService().getHost();
        if (scanned_hosts.contains(host)) {
            return new ArrayList<>();
        }
        this.scanned_hosts.add(host);
        List<IScanIssue> issues = new ArrayList<>();
        issues.addAll(this.interestingFileScan(baseRequestResponse));
        return issues;
    }

    @Override
    public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consolidateDuplicateIssues'");
    }

    private List<IScanIssue> interestingFileScan(IHttpRequestResponse baseRequestResponse) {
        List<IScanIssue> issues = new ArrayList<>();
        for (List<String> mapping : interestingFileMappings) {
            String url = mapping.get(0);
            String expect = mapping.get(1);
            String reason = mapping.get(2);
            IHttpRequestResponse attack = fetchURL(baseRequestResponse, url);

            if (safeBytesToString(attack.getResponse()).contains(expect)) {
                IHttpRequestResponse baseline = fetchURL(baseRequestResponse, url.substring(0, url.length() - 1));
                if (!safeBytesToString(baseline.getResponse()).contains(expect)) {
                    // issues.add();
                    // TODO: implement CustomScanIssue
                }
            }
        }

        return issues;
    }

    private IHttpRequestResponse fetchURL(IHttpRequestResponse baseRequestResponse, String url) {
        // Implement this method to fetch the URL and return the response.
        // You can use the helpers.buildHttpRequest() and callbacks.makeHttpRequest()
        // methods.
        return null;
    }

    private String safeBytesToString(byte[] bytes) {
        if (bytes == null) {
            bytes = new byte[0];
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
