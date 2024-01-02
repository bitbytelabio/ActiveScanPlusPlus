package io.bitbytelab.activescanpp;

import java.util.List;
import java.util.ArrayList;
import burp.*;

public class PerRequestScans implements IScannerCheck {
    private List<String> IScannerCheck;
    private IExtensionHelpers helpers;
    private IBurpExtenderCallbacks callbacks;

    public PerRequestScans(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
        // TODO Auto-generated constructor stub
        throw new UnsupportedOperationException("Unimplemented constructor 'PerRequestScans'");
    }

    @Override
    public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse) {
        return new ArrayList<>();
    }

    @Override
    public List<IScanIssue> doActiveScan(IHttpRequestResponse baseRequestResponse,
            IScannerInsertionPoint insertionPoint) {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doActiveScan'");
    }

    @Override
    public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consolidateDuplicateIssues'");
    }

    private boolean shouldTriggerPerRequestAttacks(IHttpRequestResponse baseRequestResponse,
            IScannerInsertionPoint insertionPoint) {
        IRequestInfo request = helpers.analyzeRequest(baseRequestResponse.getRequest());
        List<IParameter> params = request.getParameters();
        if (params.size() > 0) {
            int first_parameter_offset = 999999;
            IParameter first_parameter = null;
            for (IParameter param : params) {
                if (param.getType() == IParameter.PARAM_URL) {
                    if (param.getName().length() < first_parameter_offset) {
                        first_parameter_offset = param.getName().length();
                        first_parameter = param;
                    }
                }
            }
        }
        return false;
    }

}
