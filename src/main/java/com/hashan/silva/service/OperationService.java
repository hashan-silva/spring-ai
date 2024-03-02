package com.hashan.silva.service;

import com.hashan.silva.model.Analysis;
import org.apache.commons.lang3.NotImplementedException;

public interface OperationService {
    default Analysis generateAnalysis() {
        throw new NotImplementedException("Not Implemented");
    }
}
