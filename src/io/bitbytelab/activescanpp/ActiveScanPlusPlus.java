package io.bitbytelab.activescanpp;

import burp.api.montoya.scanner.AuditResult;
import burp.api.montoya.scanner.ConsolidationAction;
import burp.api.montoya.scanner.ScanCheck;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import java.nio.charset.StandardCharsets;

public class ActiveScanPlusPlus implements ScanCheck, BurpExtension {

    @Override
    public void initialize(MontoyaApi api) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    public AuditResult activeAudit(HttpRequestResponse baseRequestResponse, AuditInsertionPoint auditInsertionPoint) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activeAudit'");
    }

    @Override
    public AuditResult passiveAudit(HttpRequestResponse baseRequestResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'passiveAudit'");
    }

    @Override
    public ConsolidationAction consolidateIssues(AuditIssue newIssue, AuditIssue existingIssue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consolidateIssues'");
    }

    static String safeBytesToString(byte[] bytes) {
        if (bytes == null) {
            bytes = new byte[0];
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    static String htmlEncode(String string) {
        if (string == null) {
            return null;
        }
        return string.replace("<", "&lt;").replace(">", "&gt;");
    }
}