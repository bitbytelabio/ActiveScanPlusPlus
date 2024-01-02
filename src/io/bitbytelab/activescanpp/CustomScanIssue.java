package io.bitbytelab.activescanpp;

import java.net.URL;
import burp.IHttpRequestResponse;
import burp.IHttpService;
import burp.IScanIssue;

public class CustomScanIssue implements IScanIssue {
    private IHttpService HttpService;
    private URL Url;
    private String Name;
    private IHttpRequestResponse[] HttpMessages;
    private String Detail;
    private String Severity;
    private String Confidence;

    public CustomScanIssue(IHttpService HttpService, URL Url, String Name,
            IHttpRequestResponse[] HttpMessages, String Detail,
            String Severity, String Confidence) {
        this.HttpService = HttpService;
        this.Url = Url;
        this.Name = Name;
        this.HttpMessages = HttpMessages;
        this.Detail = Detail;
        this.Severity = Severity;
        this.Confidence = Confidence;
        System.out.println(String.format("Reported: %s on %s", Name, Url));
        return;
    }

    @Override
    public URL getUrl() {
        return this.Url;
    }

    @Override
    public String getIssueName() {
        return this.Name;
    }

    @Override
    public int getIssueType() {
        return 0;
    }

    @Override
    public String getSeverity() {
        return this.Severity;
    }

    @Override
    public String getConfidence() {
        return this.Confidence;
    }

    @Override
    public String getIssueBackground() {
        return null;
    }

    @Override
    public String getRemediationBackground() {
        return null;
    }

    @Override
    public String getIssueDetail() {
        return this.Detail;
    }

    @Override
    public String getRemediationDetail() {
        return null;
    }

    @Override
    public IHttpRequestResponse[] getHttpMessages() {
        return this.HttpMessages;
    }

    @Override
    public IHttpService getHttpService() {
        return this.HttpService;
    }

}
