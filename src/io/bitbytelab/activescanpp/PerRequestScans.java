package io.bitbytelab.activescanpp;

import java.util.List;
import java.util.ArrayList;
import burp.*;

public class PerRequestScans implements IScannerCheck {
    private List<IScannerCheck> scanChecks;
    private IExtensionHelpers helpers;
    private IBurpExtenderCallbacks callbacks;

    public PerRequestScans(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();

        // TODO: Add your own scan checks here
        return;
    }

    @Override
    public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse) {
        return new ArrayList<>();
    }

    @Override
    public List<IScanIssue> doActiveScan(IHttpRequestResponse baseRequestResponse,
            IScannerInsertionPoint insertionPoint) {
        if (!shouldTriggerPerRequestAttacks(baseRequestResponse, insertionPoint)) {
            return new ArrayList<>();
        }
        List<IScanIssue> issues = new ArrayList<>();
        for (IScannerCheck scanCheck : scanChecks) {
            try {
                // issues.addAll(scanCheck.check(baseRequestResponse));
            } catch (Exception e) {
                // System.out.println("Error executing PerRequestScans." + scanCheck.getName() +
                // ": ");
                e.printStackTrace();
            }
        }
        return issues;
    }

    @Override
    public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue) {
        return isSameIssue(existingIssue, newIssue);
    }

    private boolean shouldTriggerPerRequestAttacks(IHttpRequestResponse baseRequestResponse,
            IScannerInsertionPoint insertionPoint) {
        IRequestInfo request = helpers.analyzeRequest(baseRequestResponse.getRequest());
        List<IParameter> params = request.getParameters();
        if (params.size() > 0) {
            int firstParameterOffset = 999999;
            IParameter firstParameter = null;
            for (byte paramType : new byte[] { IParameter.PARAM_BODY, IParameter.PARAM_URL, IParameter.PARAM_JSON,
                    IParameter.PARAM_XML, IParameter.PARAM_XML_ATTR, IParameter.PARAM_MULTIPART_ATTR,
                    IParameter.PARAM_COOKIE }) {
                for (IParameter param : params) {
                    if (param.getType() != paramType) {
                        continue;
                    }
                    if (param.getNameStart() < firstParameterOffset) {
                        firstParameterOffset = param.getNameStart();
                        firstParameter = param;
                    }
                }
                if (firstParameter != null) {
                    break;
                }
            }

            if (firstParameter != null && firstParameter.getName() == insertionPoint.getInsertionPointName()) {
                return true;
            }
        } else if (insertionPoint.getInsertionPointType() == IScannerInsertionPoint.INS_HEADER
                && insertionPoint.getInsertionPointName() == "User-Agent") {
            return true;
        }
        return false;
    }

    private int isSameIssue(IScanIssue existingIssue, IScanIssue newIssue) {
        if (existingIssue.getIssueName() == newIssue.getIssueName()) {
            return -1;
        }
        return 0;
    }

}
