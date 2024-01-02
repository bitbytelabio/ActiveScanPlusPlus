package io.bitbytelab.activescanpp;

import java.util.List;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.AuditResult;
import burp.api.montoya.scanner.ConsolidationAction;
import burp.api.montoya.scanner.ScanCheck;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;
import java.net.URISyntaxException;

public class PerHostScans implements ScanCheck {
    List<String> scanned_hosts;

    @Override
    public AuditResult activeAudit(HttpRequestResponse baseRequestResponse, AuditInsertionPoint auditInsertionPoint) {
        URI uri = null;
        try {
            uri = new URI(baseRequestResponse.request().url());
        } catch (URISyntaxException e) {
            return null;
        }
        String host = uri.getHost();
        if (scanned_hosts.contains(host)) {
            return null;
        }
        scanned_hosts.add(host);
        List<AuditIssue> issues = null;

        throw new UnsupportedOperationException("Unimplemented method 'activeAudit'");
    }

    @Override
    public AuditResult passiveAudit(HttpRequestResponse baseRequestResponse) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConsolidationAction consolidateIssues(AuditIssue newIssue, AuditIssue existingIssue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consolidateIssues'");
    }

    static List<AuditIssue> interestingFileScan(HttpRequestResponse baseRequestResponse) {
        List<AuditIssue> issues = null;

        for (Map.Entry<String, String> interestingFileMapping : interestingFileMappings().entrySet()) {
            String interestingFile = interestingFileMapping.getKey();
            String interestingFileContent = interestingFileMapping.getValue();

        }

        return issues;
    }

    static Map<String, String> interestingFileMappings() {
        Map<String, String> interestingFileMappings = new HashMap<>();
        interestingFileMappings.put("/.git/config", "[core]");
        interestingFileMappings.put("/server-status", "Server uptime");
        interestingFileMappings.put("/.well-known/apple-app-site-association", "applinks");
        return interestingFileMappings;
    }

    static void fetchURI(HttpRequestResponse baseRequestResponse, String url) {
        String path = baseRequestResponse.request().path();
        var newRequest = baseRequestResponse.request().path();
    }

}
